package com.hotshot.bill.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hotshot.bill.databinding.ActivityMainBinding
import com.hotshot.bill.databinding.InvoiceItemsBinding
import kotlin.math.ceil

class InvoicesAdapter(private val context: Context, private val list: ArrayList<String>):
    RecyclerView.Adapter<InvoicesAdapter.Holder>() {
    class Holder(binding: InvoiceItemsBinding) : RecyclerView.ViewHolder(binding.root) {
        val custName = binding.custName
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(InvoiceItemsBinding.inflate(LayoutInflater.from(context)))
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.custName.text = list[position]
    }

    override fun getItemCount(): Int {
        return list.size
    }
}