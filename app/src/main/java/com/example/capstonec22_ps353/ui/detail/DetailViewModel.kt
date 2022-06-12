package com.example.capstonec22_ps353.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.capstonec22_ps353.model.AddCart
import com.example.capstonec22_ps353.model.ListCartItem
import com.example.capstonec22_ps353.model.MarketResponse
import com.example.capstonec22_ps353.repository.MarketRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val marketRepository: MarketRepository) : ViewModel() {

    val listCart: LiveData<List<ListCartItem>> = marketRepository.listCart

    val resAdd: LiveData<MarketResponse> = marketRepository.resAdd

    val result: LiveData<Boolean> = marketRepository.result

    fun addToCart(addCart: AddCart) = marketRepository.addToCart(addCart)

    fun editQty(productId: Int, qty: Int) = marketRepository.editQty(productId, qty)

    fun getCartByUserId(userId: Int) = marketRepository.getCartByUserId(userId)

//
//    fun getDetailProduct(product_id: Int) = marketRepository.getDetailProduct(product_id)

}