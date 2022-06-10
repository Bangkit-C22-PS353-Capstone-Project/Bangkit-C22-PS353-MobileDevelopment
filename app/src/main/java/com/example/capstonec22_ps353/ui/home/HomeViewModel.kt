package com.example.capstonec22_ps353.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.capstonec22_ps353.model.ListProductItem
import com.example.capstonec22_ps353.repository.MarketRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val marketRepository: MarketRepository) : ViewModel () {

    val listProduct: LiveData<List<ListProductItem>> = marketRepository.listProduct

//    fun getAllProduk() = marketRepository.getAllProduk()

}