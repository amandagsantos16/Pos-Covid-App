package com.amanda.poscovid.modelo

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class Agendamento {
    var id: String? = null
    var psicologoId: String? = null
    var pacienteId: String? = null
    var horarioId: String? = null
    var data: String? = null
    var psicologo: Psicologo? = null
    var horario: Horario? = null
    var paciente: Paciente? = null
    var statusAgendamento: Status? = null
}