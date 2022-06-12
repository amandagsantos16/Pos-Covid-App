package com.amanda.poscovid.api.service

import com.amanda.poscovid.modelo.Agendamento
import retrofit2.Call
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface PacienteService {

    @POST("api/pacientes/agendamentos")
    fun buscaAgendamentos(
        @Header("Authorization") authorization: String,
        @Query("pacienteId") pacienteId: String
    ): Call<List<Agendamento>>
}