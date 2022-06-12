package com.example.capstonec22_ps353.preferences

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.capstonec22_ps353.model.UserModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PrefViewModel @Inject constructor(private val pref: MarketPreferences) : ViewModel() {
    fun getInfo(): LiveData<UserModel> {
        return pref.getInfo().asLiveData()
    }

    fun saveInfo(userModel: UserModel) {
        viewModelScope.launch {
            pref.saveInfo(userModel)
        }
    }

    fun logout() {
        viewModelScope.launch {
            pref.logout()
        }
    }

}