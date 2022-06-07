package com.amanda.poscovid.api.service

import com.amanda.poscovid.modelo.NovaConta
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {

    @POST("identidade/nova-conta")
    fun cadastrarUsuario(
        @Body conta: NovaConta
    ): Call<Void>
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