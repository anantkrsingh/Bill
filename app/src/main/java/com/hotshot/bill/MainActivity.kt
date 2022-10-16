package com.hotshot.bill

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.firebase.analytics.FirebaseAnalytics
import com.hotshot.bill.Fragments.Dashboard
import com.hotshot.bill.Fragments.Invoicing
import com.hotshot.bill.Fragments.ShopInfo
import com.hotshot.bill.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
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
    }
    private fun setFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_frame,fragment)
        transaction.disallowAddToBackStack()
        transaction.commit()
    }
}