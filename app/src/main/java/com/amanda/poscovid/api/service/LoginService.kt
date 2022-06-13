package com.amanda.poscovid.api.service

import com.amanda.poscovid.api.modelo.LoginRetorno
import com.amanda.poscovid.modelo.NovaConta
import com.amanda.poscovid.modelo.UsuarioLogin
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

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
        @Query("usuarioId") usuarioId: String
    ): Call<List<Any>>

}