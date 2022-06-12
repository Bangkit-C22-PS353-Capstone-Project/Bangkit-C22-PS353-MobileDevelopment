package com.example.capstonec22_ps353.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.capstonec22_ps353.model.LoginResult
import com.example.capstonec22_ps353.repository.MarketRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val marketRepository: MarketRepository) : ViewModel() {

    val loginResult: LiveData<LoginResult> = marketRepository.loginResult

    val isLoading: LiveData<Boolean> = marketRepository.isLoading

    fun loginAccount(email: String, password:String) = marketRepository.loginAccount(email, password)

}