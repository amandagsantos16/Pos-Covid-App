package com.amanda.poscovid.api.client

import com.amanda.poscovid.api.modelo.RespostaCovid
import com.amanda.poscovid.api.modelo.RespostaWebClient
import com.amanda.poscovid.modelo.Estado

class CovidWebClient : WebClient() {

    private val service = retrofit.covidService

    fun buscaListaDeEstados(retorno: (RespostaWebClient<RespostaCovid<List<Estado>>>?) -> Unit) {
        service.todosEstados().executaRequest(retorno)
    }
}