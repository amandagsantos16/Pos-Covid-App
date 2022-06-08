package com.amanda.poscovid.api.modelo

import com.amanda.poscovid.modelo.UsuarioToken
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class LoginRetorno() {
    var accessToken: String? = String()
    var usuarioToken: UsuarioToken? = UsuarioToken()
}
