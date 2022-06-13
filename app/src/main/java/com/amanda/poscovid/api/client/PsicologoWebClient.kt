package com.amanda.poscovid.api.client

import com.amanda.poscovid.api.modelo.HorarioDia
import com.amanda.poscovid.api.modelo.PostHorario
import com.amanda.poscovid.api.modelo.RespostaWebClient
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
}