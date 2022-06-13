package com.amanda.poscovid.modelo

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.io.Serializable

@JsonIgnoreProperties(ignoreUnknown = true)
class UsuarioToken: Serializable {
    var id: String? = String()
    var pacienteId: String? = String()
    var psicologoId: String? = String()
    var email: String? = String()
}