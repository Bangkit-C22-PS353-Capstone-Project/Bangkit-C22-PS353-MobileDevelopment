package com.example.capstonec22_ps353.model

import android.graphics.drawable.Drawable

data class AddCart(
    val userId: Int,
    val productId: Int,
    val name: String,
    val price: Int,
    val stock: Int,
    val image: String,
    val qty: Int

//    val userId: Int,
//    val productId: Int,
//    val Title: String,
//    val Date: String,
//    val Price: String,
//    val Presentase: String,
//    val Indicator: Drawable?,
//    val qty: Int
)