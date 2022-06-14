package com.amanda.poscovid.api.client

import com.amanda.poscovid.api.modelo.PostAgendamento
import com.amanda.poscovid.api.modelo.PutAgendamento
import com.amanda.poscovid.api.modelo.RespostaWebClient
import com.amanda.poscovid.modelo.Agendamento
import com.amanda.poscovid.modelo.Horario
import com.amanda.poscovid.modelo.Psicologo
import com.amanda.poscovid.preferences.ITokenPreferenceHelper
import com.amanda.poscovid.preferences.IUserPreferenceHelper

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

    fun agendarHorario(psicologo: Psicologo, horario: Horario, data: String, retorno: (RespostaWebClient<Any>?) -> Unit) {
        val agendamento = PostAgendamento().also {
            it.data = data
            it.horarioId = horario.id
            it.psicologoId = psicologo.id
            it.pacienteId = userManager.pacienteId
        }
        service.agendar(bearerToken(), agendamento).executaRequest(retorno)
    }

    fun alterarAgendamento(agendamento: Agendamento, horario: Horario, data: String, retorno: (RespostaWebClient<Void>?) -> Unit) {
        val putAgendamento = PutAgendamento().also {
            it.data = data
            it.horarioId = horario.id
            it.id = agendamento.id
        }
        service.alterarAgendamento(bearerToken(), putAgendamento).executaRequest(retorno)
    }

    fun confrmarAgendamento(agendamentoId: String, retorno: (RespostaWebClient<Void>?) -> Unit) {
        service.confirmarAgendamento(bearerToken(), agendamentoId).executaRequest(retorno)
    }

    fun deletarAgendamento(agendamentoId: String, retorno: (RespostaWebClient<Void>?) -> Unit) {
        service.deletarAgendamento(bearerToken(), agendamentoId).executaRequest(retorno)
    }
}