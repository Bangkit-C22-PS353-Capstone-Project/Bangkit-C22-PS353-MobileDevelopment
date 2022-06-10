package com.example.capstonec22_ps353.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class MarketResponse(
	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("listCartItem")
	val listCartItem: List<ListCartItem>,

	@field:SerializedName("listProductItem")
	val listProductItem: List<ListProductItem>
)

@Parcelize
data class ListProductItem(

	@field:SerializedName("product_id")
	val productId: Int,

	@field:SerializedName("category_id")
	val categoryId: Int,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("price")
	val price: Int,

	@field:SerializedName("stock")
	val stock: Int,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("location")
	val location: String,

	@field:SerializedName("image_url")
	val imageUrl: String,

): Parcelable

@Parcelize
data class ListCartItem (

	@field:SerializedName("cart_id")
	val cartId: Int,

	@field:SerializedName("user_id")
	val userId: Int,

	@field:SerializedName("product_id")
	val productId: Int,

	@field:SerializedName("category_id")
	val categoryId: Int,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("price")
	val price: Int,

	@field:SerializedName("stock")
	val stock: Int,

	@field:SerializedName("image_url")
	val imageUrl: String,

	@field:SerializedName("qty")
	val qty: Int,

): Parcelable
