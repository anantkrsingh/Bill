package com.hotshot.bill

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hotshot.bill.databinding.ActivityNewInvoiceBinding

class NewInvoice : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val binding:ActivityNewInvoiceBinding
        super.onCreate(savedInstanceState)
        binding = ActivityNewInvoiceBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbarNewInv)
    }
}