package com.example.capstonec22_ps353.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.example.capstonec22_ps353.api.ApiService
import com.example.capstonec22_ps353.model.AddCart
import com.example.capstonec22_ps353.model.ListCartItem
import com.example.capstonec22_ps353.model.ListProductItem
import com.example.capstonec22_ps353.model.MarketResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MarketRepository @Inject constructor(private val apiService: ApiService) {

    private val _listProduct = MutableLiveData<List<ListProductItem>>()
    val listProduct: LiveData<List<ListProductItem>> = _listProduct

    private val _listCart = MutableLiveData<List<ListCartItem>>()
    val listCart: LiveData<List<ListCartItem>> = _listCart

    private val _resAdd = MutableLiveData<MarketResponse>()
    val resAdd: LiveData<MarketResponse> = _resAdd

    private val _detailProduct = MutableLiveData<ListProductItem>()
    val detailProduct: LiveData<ListProductItem> = _detailProduct

//    private val userId = 1

    init {
        getAllProduct()
//        getCartByUser()
    }

    private fun getAllProduct() {
        val client = apiService.getAllProduct()
        client.enqueue(object : Callback<MarketResponse> {
            override fun onResponse(
                call: Call<MarketResponse>,
                response: Response<MarketResponse>
            ) {
                if (response.isSuccessful) {
                    _listProduct.value = response.body()?.listProductItem
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<MarketResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

//    fun getAllProduk(): LiveData<Result<List<ListProductItem>>> = liveData {
//        emit(Result.Loading)
//        try {
//            val response = apiService.getAllProduk()
//            emit(Result.Success(response.listProductItem))
//        } catch (exception: Exception) {
//            Log.d(TAG, "getStoryLocation: ${exception.message.toString()}")
//            emit(Result.Error(exception.message.toString()))
//        }
//    }

    fun getAllCart() : LiveData<List<ListCartItem>> = liveData {
        val client = apiService.getAllCart()
        client.enqueue(object : Callback<MarketResponse> {
            override fun onResponse(
                call: Call<MarketResponse>,
                response: Response<MarketResponse>
            ) {
                if (response.isSuccessful) {
                    _listCart.value = response.body()?.listCartItem
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<MarketResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    fun getCartByUser(userId: Int) {
        val client = apiService.getCartByProduct(userId)
        client.enqueue(object : Callback<MarketResponse> {
            override fun onResponse(
                call: Call<MarketResponse>,
                response: Response<MarketResponse>
            ) {
                if (response.isSuccessful) {
                    _listCart.value = response.body()?.listCartItem
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<MarketResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    fun addToCart(addCart: AddCart){
        val client = apiService.addToCart(addCart.userId, addCart.productId, addCart.name, addCart.price, addCart.stock, addCart.image, addCart.qty)
        client.enqueue(object : Callback<MarketResponse> {
            override fun onResponse(
                call: Call<MarketResponse>,
                response: Response<MarketResponse>
            ) {
                if (response.isSuccessful) {
                    _resAdd.value = response.body()
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<MarketResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    companion object {
        private const val TAG = "MarketRepository"
    }

}