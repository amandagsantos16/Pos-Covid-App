package com.amanda.poscovid.api.modelo

class RespostaWebClient<T>(
    val dados: T? = null,
    val detalhes: DetalheWebClient? = null
)