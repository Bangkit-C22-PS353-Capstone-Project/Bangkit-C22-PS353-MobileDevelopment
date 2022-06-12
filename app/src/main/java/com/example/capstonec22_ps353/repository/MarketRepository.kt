package com.example.capstonec22_ps353.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.example.capstonec22_ps353.api.ApiService
import com.example.capstonec22_ps353.model.*
import com.example.capstonec22_ps353.preferences.MarketPreferences
import kotlinx.coroutines.flow.first
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MarketRepository @Inject constructor(private val apiService: ApiService, private val preferences: MarketPreferences) {

    private val _listProduct = MutableLiveData<List<ListProductItem>>()
    val listProduct: LiveData<List<ListProductItem>> = _listProduct

    private val _listCart = MutableLiveData<List<ListCartItem>>()
    val listCart: LiveData<List<ListCartItem>> = _listCart

    private val _resAdd = MutableLiveData<MarketResponse>()
    val resAdd: LiveData<MarketResponse> = _resAdd

    private val _detailProduct = MutableLiveData<ListProductItem>()
    val detailProduct: LiveData<ListProductItem> = _detailProduct

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _result = MutableLiveData<Boolean>()
    val result: LiveData<Boolean> = _result

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

    fun loginAccount(email: String, password: String) {
        _isLoading.value = true
        val client = apiService.loginUser(email, password)
        client.enqueue(object : Callback<MarketResponse> {
            override fun onResponse(call: Call<MarketResponse>, response: Response<MarketResponse>) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _loginResult.value = response.body()?.loginResult
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<MarketResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    fun getCartByUserId(userId: Int) {
        val client = apiService.getCartByUser(userId)
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

//    fun getCartByUser(userId: Int) {
//        val client = apiService.getCartByUser(userId)
//        client.enqueue(object : Callback<MarketResponse> {
//            override fun onResponse(
//                call: Call<MarketResponse>,
//                response: Response<MarketResponse>
//            ) {
//                if (response.isSuccessful) {
//                    _listCart.value = response.body()?.listCartItem
//                } else {
//                    Log.e(TAG, "onFailure: ${response.message()}")
//                }
//            }
//
//            override fun onFailure(call: Call<MarketResponse>, t: Throwable) {
//                Log.e(TAG, "onFailure: ${t.message}")
//            }
//        })
//    }

    fun addToCart(addCart: AddCart){
        _result.value = false
        val client = apiService.addToCart(addCart.userId, addCart.productId, addCart.name, addCart.price, addCart.stock, addCart.image, addCart.qty)
        client.enqueue(object : Callback<MarketResponse> {
            override fun onResponse(
                call: Call<MarketResponse>,
                response: Response<MarketResponse>
            ) {
                if (response.isSuccessful) {
                    _resAdd.value = response.body()
                    _result.value = true
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<MarketResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    fun editQty(productId: Int, qty: Int){
        _result.value = false
        val client = apiService.editQty(productId, qty)
        client.enqueue(object : Callback<MarketResponse> {
            override fun onResponse(
                call: Call<MarketResponse>,
                response: Response<MarketResponse>
            ) {
                if (response.isSuccessful) {
                    _resAdd.value = response.body()
                    _result.value = false

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