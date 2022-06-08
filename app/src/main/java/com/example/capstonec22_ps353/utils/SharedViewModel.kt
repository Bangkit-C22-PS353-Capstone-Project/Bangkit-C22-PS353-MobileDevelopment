package com.example.capstonec22_ps353.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController

class SharedViewModel : ViewModel() {
    private val _navController = MutableLiveData<NavController>()
    val navController: LiveData<NavController> = _navController

    private val _title = MutableLiveData<String>()
    val title: LiveData<String> = _title

    private val _titlePrice = MutableLiveData("Beras")
    val titlePrice: LiveData<String> = _titlePrice

    fun setNavController(navController2: NavController) {
        _navController.value = navController2
    }

    fun setTitle(title: String) {
        _title.value = title
    }

    fun setTitlePrice(titlePrice: String) {
        _titlePrice.value = titlePrice
    }

//    companion object {
//        const val TAG = "SharedViewModel"
//    }

}