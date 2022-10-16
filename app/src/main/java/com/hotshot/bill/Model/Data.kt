package com.hotshot.bill.Model

data class ProductData(var productName:String, var productMrp:String,var sellingPrice:String,var productDiscount:String)
data class ProductModel(var ProductList:ArrayList<ProductData>)