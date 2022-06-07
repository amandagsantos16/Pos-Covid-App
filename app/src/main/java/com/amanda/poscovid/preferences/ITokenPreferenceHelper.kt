package com.amanda.poscovid.preferences

interface ITokenPreferenceHelper {

    fun clearPrefs()

//    var token: Token

    val accessToken: String
}