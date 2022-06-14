package com.amanda.poscovid.preferences

interface ITokenPreferenceHelper {

    fun clearPrefs()

    var accessToken: String

    var notificacoes: Int
}