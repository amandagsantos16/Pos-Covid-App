package com.amanda.poscovid.modelo

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class NovaConta {
    var email: String? = String()
    var senha: String? = String()
    var senhaConfirmacao: String? = String()
}