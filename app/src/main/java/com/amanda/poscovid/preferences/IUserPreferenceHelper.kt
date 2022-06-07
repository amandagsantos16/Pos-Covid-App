package com.amanda.poscovid.preferences

interface IUserPreferenceHelper {

    fun clearPrefs()

//    var usuario: Usuario

    val id: Int

    val email: String

    val senha: String
}