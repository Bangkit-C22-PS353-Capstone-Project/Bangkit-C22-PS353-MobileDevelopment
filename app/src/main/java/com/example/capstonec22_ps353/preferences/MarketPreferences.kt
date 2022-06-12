package com.example.capstonec22_ps353.preferences

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.capstonec22_ps353.model.UserModel
import com.example.capstonec22_ps353.ui.dataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MarketPreferences @Inject constructor(@ApplicationContext val context: Context) {
    private val dataStore = context.dataStore

    fun getInfo(): Flow<UserModel> {
        return dataStore.data.map { preferences ->
            UserModel(
                preferences[ID_KEY] ?: "",
                preferences[NAME_KEY] ?: "",
                preferences[ADDRESS_KEY] ?: "",
                preferences[NOHP_KEY] ?: "",
                preferences[LOGIN_KEY] ?: false
            )
        }
    }

    suspend fun saveInfo(userModel: UserModel) {
        dataStore.edit { preferences ->
            preferences[ID_KEY] = userModel.userId
            preferences[NAME_KEY] = userModel.username
            preferences[ADDRESS_KEY] = userModel.address
            preferences[NOHP_KEY] = userModel.noHp
            preferences[LOGIN_KEY] = userModel.isLogin
        }
    }

    suspend fun logout() {
        dataStore.edit { preferences ->
            preferences[LOGIN_KEY] = false
        }
    }

    companion object {
        private val ID_KEY = stringPreferencesKey("userId")
        private val NAME_KEY = stringPreferencesKey("name")
        private val ADDRESS_KEY = stringPreferencesKey("address")
        private val NOHP_KEY = stringPreferencesKey("noHp")
        private val LOGIN_KEY = booleanPreferencesKey("state")
    }

}