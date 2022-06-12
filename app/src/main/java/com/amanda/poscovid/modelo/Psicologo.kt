package com.amanda.poscovid.modelo

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class Psicologo {
    val id: String? = null
    val nome: String? = null
    val dataNascimento: String? = null
    val crp: String? = null
    val resumo: String? = null
    val especializacoes: String? = null
    val usuarioId: String? = null
    val registroValido: Boolean = false
    val usuario: Any? = null
    val horarios: List<String>? = null
    val agendamentos: List<Any>? = null
}