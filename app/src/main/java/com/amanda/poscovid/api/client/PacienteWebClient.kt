package com.amanda.poscovid.api.client

import com.amanda.poscovid.api.modelo.RespostaWebClient
import com.amanda.poscovid.modelo.Agendamento
import com.amanda.poscovid.modelo.Horario
import com.amanda.poscovid.modelo.Psicologo
import com.amanda.poscovid.preferences.ITokenPreferenceHelper
import com.amanda.poscovid.preferences.IUserPreferenceHelper
import okhttp3.internal.userAgent

class PacienteWebClient(tokenManager: ITokenPreferenceHelper, private val userManager: IUserPreferenceHelper) : WebClient(tokenManager) {

    private val service = retrofit.pacienteService

    fun getAgendamentos(retorno: (RespostaWebClient<List<Agendamento>>?) -> Unit) {
        service.buscaAgendamentos(bearerToken(), userManager.pacienteId).executaRequest(retorno)
    }

    fun getPsicologos(retorno: (RespostaWebClient<List<Psicologo>>?) -> Unit) {
        service.buscaTodosPsicologos(bearerToken(), userManager.id).executaRequest(retorno)
    }

    fun getHorariosDisponiveis(data: String, psicologoId: String, retorno: (RespostaWebClient<List<Horario>>?) -> Unit) {
        service.buscaHorariosDisponiveis(bearerToken(), data, psicologoId).executaRequest(retorno)
    }
}