package com.example.capstonec22_ps353.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product (
    val Image: String,
    val Title: String,
    val Price: String,
    val Location: String,
): Parcelable