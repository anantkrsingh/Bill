package com.hotshot.bill

import android.os.Bundle
import android.util.Log
import android.widget.AdapterView.OnItemClickListener
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
import com.hotshot.bill.Adapters.ProductsAdapter
import com.hotshot.bill.Adapters.SuggestionAdapter
import com.hotshot.bill.Model.ProductData
import com.hotshot.bill.Model.ProductModel
import com.hotshot.bill.Utils.FirestoreHelper
import com.hotshot.bill.databinding.ActivityNewInvoiceBinding


class NewInvoice : AppCompatActivity(), (ProductData) -> Unit {
    lateinit var binding:ActivityNewInvoiceBinding
    lateinit var productList:ArrayList<ProductData>
    var pName = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewInvoiceBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbarNewInv)
        binding.addInvoice.setOnClickListener {
            uploadInvoice(binding.customerName.text.toString())
        }

        productList = ArrayList()
        binding.purchasedItemsRV.setHasFixedSize(true)
        binding.purchasedItemsRV.layoutManager = LinearLayoutManager(this)
        binding.addProduct.setOnClickListener {
            productList.add(
                ProductData(
                    pName,
                    binding.MRP.text.toString(),
                    binding.sellingPrice.text.toString(),
                    binding.discountPercent.text.toString()
                )
            )
            binding.purchasedItemsRV.adapter = ProductsAdapter(this, productList)
        }
        binding.suggestionRV.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.suggestionRV.setHasFixedSize(true)
        binding.productEditText.doOnTextChanged { text, start, before, count ->
            if (start < count || start > count) {
                MainActivity.productList.forEach {
                    if (it.productName.lowercase().contains(text.toString().lowercase())) {
                        val tempList = ArrayList<ProductData>()
                        Log.d("Suggested Item", text.toString())
                        tempList.add(it)
                        val adapter = SuggestionAdapter(this, tempList,this)
                        binding.suggestionRV.adapter = adapter

                    }
                }

            }
        }

    }
    fun uploadInvoice(custname: String) {
        FirestoreHelper.fireDatabase.collection("Shops")
            .document("Shop Name")
            .collection("Customer")
            .document(custname)
            .collection("Invoices")
            .document(System.currentTimeMillis().toString())
            .set(ProductModel(productList))
    }

    override fun invoke(p1: ProductData) {
        binding.sellingPrice.text = p1.sellingPrice
        binding.MRP.text = p1.productMrp
        pName = p1.productName
//        binding.productEditText.text = p1.productName
        binding.discountPercent.text = p1.productDiscount
    }


}