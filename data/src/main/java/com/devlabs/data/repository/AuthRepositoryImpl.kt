package com.devlabs.data.repository

import android.content.SharedPreferences
import com.devlabs.core.Constants.KEY_PIN_SHARED_PREFS
import com.devlabs.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val sharedPreferencesEditor: SharedPreferences
) : AuthRepository {

    override fun savePin(pin: String) {
        sharedPreferencesEditor.edit().putString(KEY_PIN_SHARED_PREFS, pin).apply()
    }

    override fun hasPinSaved(): Boolean {
        return sharedPreferencesEditor.getString(KEY_PIN_SHARED_PREFS, "").isNullOrEmpty().not()
    }

    override fun authPin(pin: String): Boolean {
        return sharedPreferencesEditor.getString(KEY_PIN_SHARED_PREFS, "").equals(pin)
    }
}