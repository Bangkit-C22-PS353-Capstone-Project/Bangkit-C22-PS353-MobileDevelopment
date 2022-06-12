package com.example.capstonec22_ps353.ui.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.capstonec22_ps353.model.ListCartItem
import com.example.capstonec22_ps353.repository.MarketRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(private val marketRepository: MarketRepository) : ViewModel() {

    val listCart: LiveData<List<ListCartItem>> = marketRepository.listCart

    fun getCartByUserId(userId: Int) = marketRepository.getCartByUserId(userId)

//    fun getAllCart() = marketRepository.getCartByUserId()

}