package com.example.capstonec22_ps353.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class MarketResponse(

	@field:SerializedName("loginResult")
	val loginResult: LoginResult,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("listCartItem")
	val listCartItem: List<ListCartItem>,

	@field:SerializedName("listPriceItem")
	val listPriceItem: List<ListPriceItem>,

	@field:SerializedName("listProductItem")
	val listProductItem: List<ListProductItem>
)

data class ListPriceItem (
	@field:SerializedName("price_id")
	val price_id: Int,

	@field:SerializedName("category_id")
	val categoryId: Int,

	@field:SerializedName("price")
	val price: Int,

	@field:SerializedName("month")
	val month: String,
)

data class LoginResult(

	@field:SerializedName("user_id")
	val userId: Int,

	@field:SerializedName("email")
	val email: String,

	@field:SerializedName("username")
	val username: String,

	@field:SerializedName("no_hp")
	val no_hp: String,

	@field:SerializedName("address")
	val address: String,

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
