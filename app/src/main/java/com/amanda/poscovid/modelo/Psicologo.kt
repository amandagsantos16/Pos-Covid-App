package com.amanda.poscovid.modelo

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.io.Serializable

@JsonIgnoreProperties(ignoreUnknown = true)
class Psicologo : Serializable {
    val id: String? = null
    val nome: String? = null
    val dataNascimento: String? = null
    val crp: String? = null
    val resumo: String? = null
    val especializacoes: String? = null
    val usuarioId: String? = null
    val registroValido: Boolean = false
    val usuario: Any? = null
    val horarios: List<Horario>? = null
    val agendamentos: List<Agendamento>? = null
}