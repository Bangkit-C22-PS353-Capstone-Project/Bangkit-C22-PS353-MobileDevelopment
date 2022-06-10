package com.example.capstonec22_ps353.preferences

import android.content.Context
import com.example.capstonec22_ps353.ui.dataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class MarketPreferences @Inject constructor(@ApplicationContext val context: Context) {
    private val dataStore = context.dataStore

//    fun getPrice():

//    suspend fun addPrice(price: )

}