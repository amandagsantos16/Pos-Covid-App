package com.amanda.poscovid.api.service

import com.amanda.poscovid.api.modelo.LoginRetorno
import com.amanda.poscovid.modelo.NovaConta
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {

    @POST("api/identidades/nova-conta")
    fun cadastrarUsuario(
        @Body conta: NovaConta
    ): Call<LoginRetorno>
//
//    @POST("Users/Login")
//    fun iniciaLogin(
//        @Body usuarioLogin: UsuarioLogin
//    ): Call<LoginRetorno>
//
//    @POST("Users/RecuperarSenha")
//    fun recuperarSenha(
//        @Query("cpf") cpf: String
//    ): Call<ResponseBody>
//
//    @POST("Users/ValidarCodigo")
//    fun validarCodigo(
//        @Body validarCodigo: ValidarCodigo
//    ): Call<Token>
//
//    @POST("Users/AlterarSenha")
//    fun alterarSenha(
//        @Body alterarSenha: AlterarSenha
//    ): Call<Void>

}