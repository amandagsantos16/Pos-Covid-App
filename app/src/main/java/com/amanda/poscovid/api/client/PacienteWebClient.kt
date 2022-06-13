package com.amanda.poscovid.api.client

import com.amanda.poscovid.api.modelo.RespostaWebClient
import com.amanda.poscovid.modelo.Agendamento
import com.amanda.poscovid.preferences.ITokenPreferenceHelper
import com.amanda.poscovid.preferences.IUserPreferenceHelper
import okhttp3.internal.userAgent

class PacienteWebClient(tokenManager: ITokenPreferenceHelper, private val userManager: IUserPreferenceHelper) : WebClient(tokenManager) {

    private val service = retrofit.pacienteService

    fun getAgendamentos(retorno: (RespostaWebClient<List<Agendamento>>?) -> Unit) {
        service.buscaAgendamentos(bearerToken(), userManager.pacienteId).executaRequest(retorno)
    }
}