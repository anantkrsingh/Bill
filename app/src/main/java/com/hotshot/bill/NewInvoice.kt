package com.hotshot.bill

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.hotshot.bill.Adapters.ProductsAdapter
import com.hotshot.bill.Model.ProductData
import com.hotshot.bill.Model.ProductModel
import com.hotshot.bill.Utils.FirestoreHelper
import com.hotshot.bill.databinding.ActivityNewInvoiceBinding

class NewInvoice : AppCompatActivity() {
    lateinit var binding:ActivityNewInvoiceBinding
    lateinit var productList:ArrayList<ProductData>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewInvoiceBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbarNewInv)
        binding.addInvoice.setOnClickListener {
            uploadInvoice(binding.customerName.text.toString())
        }
        productList  = ArrayList()
        binding.purchasedItemsRV.setHasFixedSize(true)
        binding.purchasedItemsRV.layoutManager = LinearLayoutManager(this)
        binding.addProduct.setOnClickListener {
           productList.add(
               ProductData(binding.productEditText.text.toString(),binding.MRP.text.toString(),binding.sellingPrice.text.toString(),
           binding.discountPercent.text.toString())
           )
            binding.purchasedItemsRV.adapter =  ProductsAdapter(this,productList)
        }
    }
    fun uploadInv(){
        val hashMap = hashMapOf<String,Any>(
            "Customer name" to "Anant",
            "Customer phone" to "00000000",
            "invoice value phone" to "00000000",
        )
        FirestoreHelper.fireDatabase.collection("Shops")
            .document("AnantGeneralStore000")
            .collection("Customers")
            .document("Ayush00000")
            .collection("invoices")
            .add(hashMap)
    }

    fun uploadInvoice(custname:String){
        FirestoreHelper.fireDatabase.collection("Shops")
            .document("Shop Name")
            .collection("Customer")
            .document(custname)
            .collection("Invoices")
            .document(System.currentTimeMillis().toString())
            .set(ProductModel(productList))
    }
}