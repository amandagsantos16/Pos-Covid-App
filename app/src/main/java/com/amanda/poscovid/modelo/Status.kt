package com.amanda.poscovid.modelo

import java.io.Serializable

enum class Status(val status: Int) : Serializable {
    PENDENTE_PSICOLOGO(1),
    CONFIRMADO(2),
    CANCELADO(3),
    CANCELADO_PACIENTE(4),
}