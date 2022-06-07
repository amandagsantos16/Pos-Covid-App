package com.amanda.poscovid.preferences

import android.content.Context
import com.amanda.poscovid.extension.get

open class TokenPreferenceManager constructor(context: Context) : ITokenPreferenceHelper {

    private val preferences = context.getSharedPreferences(PREFERENCES_KEY, Context.MODE_PRIVATE)

    override fun clearPrefs() {
        preferences.edit().clear().apply()
    }

    override val accessToken: String
        get() = preferences[TOKEN_ACCESS] ?: ""

    companion object {
        private const val PREFERENCES_KEY = "CHAVE_ORION_TOKEN_PREFERENCES"
        private const val TOKEN_CREATED = "created"
        private const val TOKEN_EXPIR = "expiration"
        private const val TOKEN_ACCESS = "accessToken"
    }
}
