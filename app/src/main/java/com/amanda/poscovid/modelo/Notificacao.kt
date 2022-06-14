package com.amanda.poscovid.modelo

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.io.Serializable

@JsonIgnoreProperties(ignoreUnknown = true)
class Notificacao : Serializable {
    var id: String? = String()
    var mensagem: String? = String()
    var psicologoId: String? = String()
    var pacienteId: String? = String()
}