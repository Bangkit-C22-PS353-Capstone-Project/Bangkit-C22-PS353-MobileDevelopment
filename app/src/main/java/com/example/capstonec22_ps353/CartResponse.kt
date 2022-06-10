package com.example.capstonec22_ps353

import com.google.gson.annotations.SerializedName

data class CartResponse(
	@field:SerializedName("CartResponse")
	val cartResponse: List<CartResponseItem>
)

data class CartResponseItem(

	@field:SerializedName("cart_id")
	val cartId: String,

	@field:SerializedName("user_id")
	val userId: String,

	@field:SerializedName("price")
	val price: String,

	@field:SerializedName("image_url")
	val imageUrl: String,

	@field:SerializedName("product_id")
	val productId: String,

	@field:SerializedName("qty")
	val qty: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("stock")
	val stock: String
)
