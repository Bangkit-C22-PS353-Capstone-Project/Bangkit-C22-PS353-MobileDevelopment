package com.example.capstonec22_ps353.ui.price

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.capstonec22_ps353.model.AddCart
import com.example.capstonec22_ps353.model.ListCartItem
import com.example.capstonec22_ps353.model.ListPriceItem
import com.example.capstonec22_ps353.model.MarketResponse
import com.example.capstonec22_ps353.repository.MarketRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PriceViewModel @Inject constructor(private val marketRepository: MarketRepository) : ViewModel()  {
    val listPrice: LiveData<List<ListPriceItem>> = marketRepository.listPrice

    fun getPriceByCategory(categoryId: Int) = marketRepository.getPriceByCategory(categoryId)
}