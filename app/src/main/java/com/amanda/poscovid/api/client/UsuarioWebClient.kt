package com.amanda.poscovid.api.client

class UsuarioWebClient : WebClient() {

    private val service = retrofit.loginService

//    fun cadastrarNovoUsuario(usuario: Usuario, retorno: (RespostaWebClient<Usuario>?) -> Unit) {
//        service.cadastrarUsuario(usuario)
//            .executaRequest(retorno)
//    }
//
//    fun iniciaSessao(usuarioLogin: UsuarioLogin, retorno: (RespostaWebClient<LoginRetorno>?) -> Unit) {
//        service.iniciaLogin(usuarioLogin)
//            .executaRequest(retorno)
//    }
//
//    fun recuperarSenha(cpf: String, retorno: (RespostaWebClient<String>?) -> Unit) {
//        service.recuperarSenha(cpf).executaRequest { respota ->
//            var email: String? = null
//            respota?.dados?.let {
//                email = JSONObject(it.string()).optString("email")
//            }
//            retorno(RespostaWebClient(email, respota?.detalhes))
//        }
//    }
//
//    fun validarCodigoDeRecuperacao(validarCodigo: ValidarCodigo, retorno: (RespostaWebClient<Token>?) -> Unit) {
//        service.validarCodigo(validarCodigo)
//            .executaRequest(retorno)
//    }
//
//    fun alterarSenha(alterarSenha: AlterarSenha, token: Token, retorno: (RespostaWebClient<Void>?) -> Unit) {
//        service.alterarSenha(token = "Bearer ${token.accessToken}", alterarSenha = alterarSenha)
//            .executaRequest(retorno)
//    }
}