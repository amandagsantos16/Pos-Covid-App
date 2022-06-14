package com.amanda.poscovid.api.client

import com.amanda.poscovid.api.modelo.HorarioDia
import com.amanda.poscovid.api.modelo.PostHorario
import com.amanda.poscovid.api.modelo.PutAgendamento
import com.amanda.poscovid.api.modelo.RespostaWebClient
import com.amanda.poscovid.modelo.Agendamento
import com.amanda.poscovid.modelo.CadastrarPsicologo
import com.amanda.poscovid.modelo.Horario
import com.amanda.poscovid.modelo.Psicologo
import com.amanda.poscovid.preferences.ITokenPreferenceHelper
import com.amanda.poscovid.preferences.IUserPreferenceHelper
import com.squareup.okhttp.ResponseBody

class PsicologoWebClient(manager: ITokenPreferenceHelper, private val userManager: IUserPreferenceHelper) : WebClient(manager) {

    private val service = retrofit.psicologoService

    fun cadastrarPsicologo(cadastrarPsicologo: CadastrarPsicologo, retorno: (RespostaWebClient<Psicologo>?) -> Unit) {
        cadastrarPsicologo.usuarioId = userManager.id
        service.cadastrarPsicologo(bearerToken(), cadastrarPsicologo).executaRequest(retorno)
    }

    fun getHorariosCadastrados(diaSemana: Int, retorno: (RespostaWebClient<List<Horario>>?) -> Unit) {
        service.buscarHorariosCadatrados(bearerToken(), diaSemana, userManager.psicologoId).executaRequest(retorno)
    }

    fun salvaHorarios(dia: Int, horarios: List<String>, retorno: (RespostaWebClient<List<Horario>>?) -> Unit) {
        val postHorario = PostHorario()
        val element = HorarioDia()
        element.diaDaSemana = dia
        element.horarios = horarios
        postHorario.psicologoId = userManager.psicologoId
        postHorario.horarios = listOf(element)
        service.salvaHorarios(bearerToken(), postHorario).executaRequest(retorno)
    }

    fun deletaHorario(horarioId: String, retorno: (RespostaWebClient<Void>?) -> Unit) {
        service.deletaHorario(bearerToken(), horarioId).executaRequest(retorno)
    }

    fun getAgendamentos(retorno: (RespostaWebClient<List<Agendamento>>?) -> Unit) {
        service.buscaAgendamentos(bearerToken(), userManager.psicologoId).executaRequest(retorno)
    }

    fun deletarAgendamento(agendamentoId: String, retorno: (RespostaWebClient<Void>?) -> Unit) {
        service.deletarAgendamento(bearerToken(), agendamentoId).executaRequest(retorno)
    }

    fun confrmarAgendamento(agendamentoId: String, retorno: (RespostaWebClient<Void>?) -> Unit) {
        service.confirmarAgendamento(bearerToken(), agendamentoId).executaRequest(retorno)
    }

    fun alterarAgendamento(agendamento: Agendamento, horario: Horario, data: String, retorno: (RespostaWebClient<Void>?) -> Unit) {
        val putAgendamento = PutAgendamento().also {
            it.data = data
            it.horarioId = horario.id
            it.id = agendamento.id
        }
        service.alterarAgendamento(bearerToken(), putAgendamento).executaRequest(retorno)
    }
}