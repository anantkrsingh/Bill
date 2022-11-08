package com.hotshot.bill.Fragments

import android.app.ActionBar.LayoutParams
import android.app.Dialog
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowInsetsController
import androidx.core.view.WindowInsetsControllerCompat
import com.google.firebase.database.collection.LLRBNode.Color
import com.hotshot.bill.R
import com.hotshot.bill.Utils.FirestoreHelper
import com.hotshot.bill.databinding.AddProductBinding
import com.hotshot.bill.databinding.FragmentShopInfoBinding
import kotlin.random.Random


class ShopInfo : Fragment() {
    lateinit var binding:FragmentShopInfoBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_shop_info, container, false)
        binding = FragmentShopInfoBinding.bind(view)
        binding.addProductsSI.setOnClickListener {
            showAddDialog()
        }
        return view
    }
    fun showAddDialog(){
        val view = LayoutInflater.from(requireContext()).inflate(R.layout.add_product,binding.root,false)
        val dialogBinding = AddProductBinding.bind(view)
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(view)
        dialog.window?.setLayout(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT)
        dialog.window?.setGravity(Gravity.BOTTOM)
        dialog.window?.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))
        dialog.show()
        dialogBinding.btnAddProduct.setOnClickListener {
            addProduct(dialogBinding)
        }
    }
    fun  addProduct(dialogBinding:AddProductBinding){
        val hashMap = hashMapOf<String,Any>(
            "Product Name" to dialogBinding.addProductName.text.toString(),
            "Product MRP" to dialogBinding.addProductMRP.text.toString(),
            "Product Price" to dialogBinding.addProductPrice.text.toString()
        )
        FirestoreHelper.fireDatabase.collection("Shops")
            .document("Shop Name")
            .collection("Products")
            .add(hashMap)
    }
}