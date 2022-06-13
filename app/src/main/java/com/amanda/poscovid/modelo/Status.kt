package com.amanda.poscovid.modelo

import java.io.Serializable

enum class Status(val status: Int) : Serializable {
    PENDENTE(1),
    CONFIRMADO(2),
    CANCELADO(3),
}