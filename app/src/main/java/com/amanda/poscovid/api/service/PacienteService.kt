package com.amanda.poscovid.api.service

import com.amanda.poscovid.modelo.Agendamento
import com.amanda.poscovid.modelo.Psicologo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface PacienteService {

    @GET("api/pacientes/agendamentos")
    fun buscaAgendamentos(
        @Header("Authorization") authorization: String,
        @Query("pacienteId") pacienteId: String
    ): Call<List<Agendamento>>

    @GET("api/psicologos")
    fun buscaTodosPsicologos(
        @Header("Authorization") authorization: String,
        @Query("usuarioId") pacienteId: String
    ): Call<List<Psicologo>>
}