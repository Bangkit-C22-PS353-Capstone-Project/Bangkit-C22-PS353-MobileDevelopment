package com.example.capstonec22_ps353.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SharedViewModel : ViewModel() {
    private val _navController = MutableLiveData<NavController>()
    val navController: LiveData<NavController> = _navController


    fun setNavController(navController2: NavController) {
        _navController.value = navController2

        Log.e(TAG, "setNavController: $navController2")

    }

    companion object {
        private val TAG = "SharedViewModel"
    }



//    var navController: NavController? = null
//    private var navController = MutableLiveData

}