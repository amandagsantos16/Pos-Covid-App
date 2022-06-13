package com.amanda.poscovid.api.service

import com.amanda.poscovid.api.modelo.PostHorario
import com.amanda.poscovid.modelo.CadastrarPsicologo
import com.amanda.poscovid.modelo.Horario
import com.amanda.poscovid.modelo.Psicologo
import com.squareup.okhttp.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface PsicologoService {

    @POST("api/psicologos")
    fun cadastrarPsicologo(
        @Header("Authorization") authorization: String,
        @Body cadastrarPsicologo: CadastrarPsicologo
    ): Call<Psicologo>

    @GET("api/psicologos/horarios-por-dia")
    fun buscarHorariosCadatrados(
        @Header("Authorization") authorization: String,
        @Query("diaDaSemana") diaSemana: Int,
        @Query("psicologoId") psicologoId: String,
    ): Call<List<Horario>>

    @POST("api/psicologos/horarios")
    fun salvaHorarios(
        @Header("Authorization") authorization: String,
        @Body postHorario: PostHorario
    ): Call<List<Horario>>
}