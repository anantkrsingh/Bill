package com.hotshot.bill

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.firestore.QuerySnapshot
import com.hotshot.bill.Fragments.Dashboard
import com.hotshot.bill.Fragments.Invoicing
import com.hotshot.bill.Fragments.ShopInfo
import com.hotshot.bill.Model.ProductData
import com.hotshot.bill.Utils.FirestoreHelper
import com.hotshot.bill.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    companion object{
        lateinit var productList : ArrayList<ProductData>
    }

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setFragment(Dashboard())
        binding.bottomNav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.dashboard -> setFragment(Dashboard())
                R.id.invoicing-> setFragment(Invoicing())
                R.id.shop_info -> setFragment(ShopInfo())
                else -> false
            }

            return@setOnItemSelectedListener true
        }
        FirebaseAnalytics.getInstance(this)
        binding.fabNewInv.setOnClickListener(View.OnClickListener {
            val intent = Intent(this,NewInvoice::class.java)
            startActivity(intent)
        })
        getProducts()
    }
    private fun setFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_frame,fragment)
        transaction.disallowAddToBackStack()
        transaction.commit()
    }
    fun getProducts(){
        productList = ArrayList()
        FirestoreHelper.fireDatabase.collection("Shops")
            .document("Shop Name")
            .collection("Products")
            .get()
            .addOnCompleteListener(object :OnCompleteListener<QuerySnapshot>{
                override fun onComplete(p0: Task<QuerySnapshot>) {
                    if (p0.isSuccessful){
                        for (i in p0.result){
                           val pName = i.get("Product Name").toString()
                           val pMRP = i.get("Product MRP").toString()
                           val pPrice = i.get("Product Price").toString()
                           val pDis = "0"
                            productList.add(ProductData(productName = pName, productMrp = pMRP, sellingPrice = pPrice, productDiscount = pDis))
                            Log.d("ListItems",productList.toString())
                        }
                    }
                }

            })
    }



}