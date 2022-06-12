package com.example.capstonec22_ps353.api


import com.example.capstonec22_ps353.model.MarketResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @GET("product.php")
    fun getAllProduct(): Call<MarketResponse>

    @GET("getAllCart.php")
    fun getAllCart(): Call<MarketResponse>

    @GET("getCartByUser.php")
    fun getCartByUser(
        @Query("user_id") user_id: Int,
    ): Call<MarketResponse>


    @FormUrlEncoded
    @POST("checkLogin.php")
    fun loginUser(
        @Field("email") email: String,
        @Field("password") password: String,
    ): Call<MarketResponse>

    @FormUrlEncoded
    @POST("cart.php")
    fun addToCart(
        @Field("user_id") user_id: Int,
        @Field("product_id") product_id: Int,
        @Field("name") name: String,
        @Field("price") price: Int,
        @Field("stock") stock: Int,
        @Field("image_url") image_url: String,
        @Field("qty") qty: Int,
    ): Call<MarketResponse>

    @FormUrlEncoded
    @POST("editQtyCart.php")
    fun editQty(
        @Field("product_id") product_id: Int,
        @Field("qty") qty: Int,
    ): Call<MarketResponse>

}