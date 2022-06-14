package com.amanda.poscovid.api.client

import com.amanda.poscovid.api.modelo.LoginRetorno
import com.amanda.poscovid.api.modelo.RespostaWebClient
import com.amanda.poscovid.modelo.Notificacao
import com.amanda.poscovid.modelo.NovaConta
import com.amanda.poscovid.modelo.UsuarioLogin
import com.amanda.poscovid.preferences.ITokenPreferenceHelper
import com.amanda.poscovid.preferences.IUserPreferenceHelper

class UsuarioWebClient(manager: ITokenPreferenceHelper, private val userManager: IUserPreferenceHelper) : WebClient(manager) {

    private val service = retrofit.loginService

    fun cadastrarNovoUsuario(conta: NovaConta, retorno: (RespostaWebClient<LoginRetorno>?) -> Unit) {
        service.cadastrarUsuario(conta)
            .executaRequest(retorno)
    }

    fun iniciaSessao(usuarioLogin: UsuarioLogin, retorno: (RespostaWebClient<LoginRetorno>?) -> Unit) {
        service.iniciaLogin(usuarioLogin)
            .executaRequest(retorno)
    }

    fun buscaNotificacoes(retorno: (RespostaWebClient<List<Notificacao>>?) -> Unit) {
        service.buscaNotificacoes(userManager.id, bearerToken())
            .executaRequest(retorno)
    }
}