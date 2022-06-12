package com.amanda.poscovid.api.service

import com.amanda.poscovid.modelo.CadastrarPsicologo
import com.amanda.poscovid.modelo.Psicologo
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface PsicologoService {

    @POST("api/psicologos")
    fun cadastrarPsicologo(
        @Header("Authorization") authorization: String,
        @Body cadastrarPsicologo: CadastrarPsicologo
    ): Call<Psicologo>
}