package com.amanda.poscovid.preferences

import android.content.Context
import com.amanda.poscovid.extension.get

open class UserPreferenceManager constructor(context: Context) : IUserPreferenceHelper {

    private val preferences = context.getSharedPreferences(PREFERENCES_KEY, Context.MODE_PRIVATE)

    override fun clearPrefs() {
        preferences.edit().clear().apply()
    }

    override val id: Int
        get() = preferences[ID] ?: -1

    override val email: String
        get() = preferences[EMAIL] ?: ""

    override val senha: String
        get() = preferences[SENHA] ?: ""


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
