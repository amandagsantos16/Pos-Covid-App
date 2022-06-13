package com.amanda.poscovid.api.service

import com.amanda.poscovid.modelo.CadastrarPsicologo
import com.amanda.poscovid.modelo.Horario
import com.amanda.poscovid.modelo.Psicologo
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface PsicologoService {

    @POST("api/psicologos")
    fun cadastrarPsicologo(
        @Header("Authorization") authorization: String,
        @Body cadastrarPsicologo: CadastrarPsicologo
    ): Call<Psicologo>

    @POST("api/psicologos/horarios")
    fun buscarHorariosCadatrados(
        @Header("Authorization") authorization: String,
        @Query("diaDaSemana") diaSemana: Int
    ): Call<List<Horario>>
}