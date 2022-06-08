package com.amanda.poscovid.preferences

import android.content.Context
import com.amanda.poscovid.extension.get
import com.amanda.poscovid.extension.set

open class UserPreferenceManager constructor(context: Context) : IUserPreferenceHelper {

    private val preferences = context.getSharedPreferences(PREFERENCES_KEY, Context.MODE_PRIVATE)

    override fun clearPrefs() {
        preferences.edit().clear().apply()
    }

    override var id: String
        get() = preferences[ID] ?: String()
        set(value) {
            preferences[ID] = value
        }

    override var email: String
        get() = preferences[EMAIL] ?: String()
        set(value) {
            preferences[EMAIL] = value
        }


    companion object {
        private const val PREFERENCES_KEY = "CHAVE_ORION_USER_PREFERENCES"
        private const val NOME = "nome"
        private const val EMAIL = "email"
        private const val SENHA = "senha"
        private const val ID = "id"
        private const val CPF = "cpf"
        private const val CELULAR = "celular"
    }
}
