package com.hotshot.bill.Fragments

import android.app.Dialog
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import com.google.firebase.database.collection.LLRBNode.Color
import com.hotshot.bill.R
import com.hotshot.bill.databinding.FragmentShopInfoBinding


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
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(view)
        dialog.window?.setGravity(Gravity.CENTER)
        dialog.window?.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))
        dialog.show()
    }
}