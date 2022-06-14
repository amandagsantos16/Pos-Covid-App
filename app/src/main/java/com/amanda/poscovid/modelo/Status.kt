package com.amanda.poscovid.modelo

import java.io.Serializable

enum class Status(val status: Int) : Serializable {
    UNKNOW(0),
    PENDENTE_PSICOLOGO(1),
    CONFIRMADO(2),
    CANCELADO(3),
    PENDENTE_PACIENTE(4),
}