package com.amanda.poscovid.api.client

import com.amanda.poscovid.api.modelo.RespostaWebClient
import com.amanda.poscovid.modelo.CadastrarPsicologo
import com.amanda.poscovid.modelo.Horario
import com.amanda.poscovid.modelo.Psicologo
import com.amanda.poscovid.preferences.ITokenPreferenceHelper

class PsicologoWebClient(manager: ITokenPreferenceHelper) : WebClient(manager) {

    private val service = retrofit.psicologoService

    fun cadastrarPsicologo(cadastrarPsicologo: CadastrarPsicologo, retorno: (RespostaWebClient<Psicologo>?) -> Unit) {
        service.cadastrarPsicologo(bearerToken(), cadastrarPsicologo).executaRequest(retorno)
    }

    fun getHorariosCadastrados(diaSemana: Int, retorno: (RespostaWebClient<List<Horario>>?) -> Unit) {
        service.buscarHorariosCadatrados(bearerToken(), diaSemana).executaRequest(retorno)
    }
}