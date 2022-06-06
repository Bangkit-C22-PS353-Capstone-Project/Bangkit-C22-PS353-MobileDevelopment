package com.example.capstonec22_ps353.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController

class SharedViewModel : ViewModel() {
    private val _navController = MutableLiveData<NavController>()
    val navController: LiveData<NavController> = _navController

    private val _titleCategory = MutableLiveData<String>()
    val titleCategory: LiveData<String> = _titleCategory

    private val _titlePrice = MutableLiveData<String>()
    val titlePrice: LiveData<String> = _titlePrice

    fun setNavController(navController2: NavController) {
        _navController.value = navController2
    }

    fun setTitle(title: String) {
        _titleCategory.value = title
    }

    fun setTitlePrice(titlePrice: String) {
        _titlePrice.value = titlePrice
    }

//    companion object {
//        const val TAG = "SharedViewModel"
//    }

}