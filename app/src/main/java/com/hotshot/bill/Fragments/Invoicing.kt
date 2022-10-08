package com.hotshot.bill.Fragments

import android.R.attr.outAnimation
import android.animation.LayoutTransition
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.Animation.AnimationListener
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.hotshot.bill.Adapters.InvoicesAdapter
import com.hotshot.bill.R
import com.hotshot.bill.databinding.FragmentInvoicingBinding


class Invoicing : Fragment() {
    val list = ArrayList<String>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_invoicing, container, false)
        val binding = FragmentInvoicingBinding.bind(view)
        list.add("Customer 1")
        list.add("Customer 2")
        list.add("Customer 3")
        list.add("Customer 4")
        list.add("Customer 5")
        binding.todayRV.setHasFixedSize(true)
        binding.todayRV.layoutManager = LinearLayoutManager(requireContext())
        binding.todayRV.adapter = InvoicesAdapter(requireContext(),list)
        binding.allInvRV.setHasFixedSize(true)
        binding.allInvRV.layoutManager = LinearLayoutManager(requireContext())
        binding.allInvRV.adapter = InvoicesAdapter(requireContext(),list)
        // For Drop Down VisiblityToggle
        binding.dropDownMenu.setOnClickListener(View.OnClickListener {
            visiblityToggle(binding.todayRV,binding.dropDownArrow,binding.parentTodayInv,binding.parentINV)
        })
        binding.dropDownMenuAnother.setOnClickListener(View.OnClickListener {
            visiblityToggle(binding.allInvRV,binding.dropDownArrowAnother,binding.parentAllInv,binding.parentINV)
        })
        return view

    }
    private fun visiblityToggle(view: View, image: ImageView, parent: LinearLayout, parentMain:LinearLayout){
        var layoutTransition: LayoutTransition
        var layoutTransitionParent: LayoutTransition
        layoutTransition = LayoutTransition()
        layoutTransitionParent = LayoutTransition()
        if (view.isVisible){
            layoutTransition.enableTransitionType(LayoutTransition.CHANGING)
            layoutTransitionParent.enableTransitionType(LayoutTransition.CHANGING)
            parentMain.layoutTransition = layoutTransitionParent
            parent.layoutTransition = layoutTransition
            view.visibility = View.GONE
            image.setImageResource(R.drawable.ic_baseline_arrow_drop_up_24)
        } else{
            layoutTransition.enableTransitionType(LayoutTransition.CHANGING)
            layoutTransitionParent.enableTransitionType(LayoutTransition.CHANGING)
            parentMain.layoutTransition = layoutTransitionParent
            parent.layoutTransition = layoutTransition
            view.visibility = View.VISIBLE
            image.setImageResource(R.drawable.ic_arrow_drop_down)
        }
    }
}