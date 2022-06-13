package com.amanda.poscovid.preferences

interface IUserPreferenceHelper {

    fun clearPrefs()

    var id: String

    var pacienteId: String

    var psicologoId: String

    var email: String
}