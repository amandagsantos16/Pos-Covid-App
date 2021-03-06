package com.amanda.poscovid.api.service

import com.amanda.poscovid.api.modelo.PostAgendamento
import com.amanda.poscovid.api.modelo.PutAgendamento
import com.amanda.poscovid.modelo.Agendamento
import com.amanda.poscovid.modelo.Horario
import com.amanda.poscovid.modelo.Psicologo
import retrofit2.Call
import retrofit2.http.*

interface PacienteService {

    @GET("api/pacientes/agendamentos")
    fun buscaAgendamentos(
        @Header("Authorization") authorization: String,
        @Query("pacienteId") pacienteId: String
    ): Call<List<Agendamento>>

    @POST("api/pacientes/agendamentos")
    fun agendar(
        @Header("Authorization") authorization: String,
        @Body agendamento: PostAgendamento
    ): Call<Any>

    @PUT("api/pacientes/agendamentos")
    fun alterarAgendamento(
        @Header("Authorization") authorization: String,
        @Body agendamento: PutAgendamento
    ): Call<Void>

    @DELETE("api/pacientes/agendamentos")
    fun deletarAgendamento(
        @Header("Authorization") authorization: String,
        @Query("agendamentoId") agendamentoId: String
    ): Call<Void>

    @GET("api/psicologos")
    fun buscaTodosPsicologos(
        @Header("Authorization") authorization: String,
        @Query("usuarioId") pacienteId: String
    ): Call<List<Psicologo>>

    @POST("api/pacientes/agendamentos/confirmacao")
    fun confirmarAgendamento(
        @Header("Authorization") authorization: String,
        @Query("agendamentoId") agendamentoId: String
    ): Call<Void>

    @GET("api/psicologos/horarios-por-data")
    fun buscaHorariosDisponiveis(
        @Header("Authorization") authorization: String,
        @Query("data") data: String,
        @Query("psicologoId") psicologoId: String
    ): Call<List<Horario>>
}