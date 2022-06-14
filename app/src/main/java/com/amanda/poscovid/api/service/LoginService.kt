package com.amanda.poscovid.api.service

import com.amanda.poscovid.api.modelo.LoginRetorno
import com.amanda.poscovid.modelo.Notificacao
import com.amanda.poscovid.modelo.NovaConta
import com.amanda.poscovid.modelo.UsuarioLogin
import retrofit2.Call
import retrofit2.http.*

interface LoginService {

    @POST("api/identidades/nova-conta")
    fun cadastrarUsuario(
        @Body conta: NovaConta
    ): Call<LoginRetorno>

    @POST("api/identidades/autenticar")
    fun iniciaLogin(
        @Body usuarioLogin: UsuarioLogin
    ): Call<LoginRetorno>

    @GET("api/notificacoes")
    fun buscaNotificacoes(
        @Query("usuarioId") usuarioId: String,
        @Header("Authorization") authorization: String
    ): Call<List<Notificacao>>

}