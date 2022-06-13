package com.amanda.poscovid.modelo

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.io.Serializable

@JsonIgnoreProperties(ignoreUnknown = true)
class Horario: Serializable {
    var id: String? = null
    var diaDaSemana: String? = null
    var hora: String? = null
    var psicologoId: String? = null
    var psicologo: Psicologo? = null
    var agendamentos: List<Agendamento>? = null
}