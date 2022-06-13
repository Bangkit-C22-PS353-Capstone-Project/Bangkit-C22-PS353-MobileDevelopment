package com.example.capstonec22_ps353.ui.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.capstonec22_ps353.model.ListProductItem
import com.example.capstonec22_ps353.repository.MarketRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(private val marketRepository: MarketRepository) : ViewModel() {

    val listProduct2: LiveData<List<ListProductItem>> = marketRepository.listProduct2

    val isLoading: LiveData<Boolean> = marketRepository.isLoading

    fun getProductByCategory(categoryId: Int) = marketRepository.getProductByCategory(categoryId)


}