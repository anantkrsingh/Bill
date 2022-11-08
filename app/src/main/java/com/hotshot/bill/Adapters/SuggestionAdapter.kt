package com.hotshot.bill.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hotshot.bill.Model.ProductData
import android.content.Context
import android.text.style.SuggestionSpan
import android.view.View
import android.widget.AdapterView.OnItemClickListener
import androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
import com.hotshot.bill.NewInvoice
import com.hotshot.bill.R
import com.hotshot.bill.databinding.SuggestionsItemBinding

class SuggestionAdapter(val context:Context, var list:ArrayList<ProductData>, private val clickListener: (ProductData) -> Unit):RecyclerView.Adapter<SuggestionAdapter.holder>() {
    class holder(val binding:SuggestionsItemBinding):RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): holder {
        return SuggestionAdapter.holder(SuggestionsItemBinding.inflate(LayoutInflater.from(context),parent,false))
    }

    override fun onBindViewHolder(holder: holder, position: Int) {
        holder.binding.suggProductName.text = list[position].productName
        holder.binding.suggProductPrice.text = list[position].sellingPrice
        holder.binding.root.setOnClickListener { clickListener.invoke(list[position]) }

    }
    override fun getItemCount(): Int {
        return list.size
    }
}