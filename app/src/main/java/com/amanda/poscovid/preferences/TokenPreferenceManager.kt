package com.amanda.poscovid.preferences

import android.content.Context
import com.amanda.poscovid.extension.get
import com.amanda.poscovid.extension.set

open class TokenPreferenceManager constructor(context: Context) : ITokenPreferenceHelper {

    private val preferences = context.getSharedPreferences(PREFERENCES_KEY, Context.MODE_PRIVATE)

    override fun clearPrefs() {
        preferences.edit().clear().apply()
    }

    override var accessToken: String
        get() = preferences[TOKEN_ACCESS] ?: ""
        set(value) {
            preferences[TOKEN_ACCESS] = value
        }

    override var notificacoes: Int
        get() = preferences[NOTIFICACOES] ?: 0
        set(value) {
            preferences[NOTIFICACOES] = value
        }

    companion object {
        private const val PREFERENCES_KEY = "CHAVE_ORION_TOKEN_PREFERENCES"
        private const val NOTIFICACOES = "notificacoes"
        private const val TOKEN_ACCESS = "accessToken"
    }
}
