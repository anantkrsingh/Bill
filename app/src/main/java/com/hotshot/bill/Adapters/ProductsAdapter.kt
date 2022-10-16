package com.hotshot.bill.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.hotshot.bill.Model.ProductData
import com.hotshot.bill.databinding.InvoiceItemsBinding
import android.content.Context

class ProductsAdapter(val context:Context, var list:ArrayList<ProductData>):RecyclerView.Adapter<ProductsAdapter.holder>() {
    class holder(val binding:InvoiceItemsBinding):RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): holder {
        return ProductsAdapter.holder(InvoiceItemsBinding.inflate(LayoutInflater.from(context)))
    }

    override fun onBindViewHolder(holder: holder, position: Int) {
        holder.binding.custName.text = list[position].productName
    }

    override fun getItemCount(): Int {
        return list.size
    }
}