package com.amanda.poscovid.api.service

import com.amanda.poscovid.api.modelo.RespostaCovid
import com.amanda.poscovid.modelo.Estado
import retrofit2.Call
import retrofit2.http.GET

interface CovidService {

    @GET("api/report/v1")
    fun todosEstados(): Call<RespostaCovid<List<Estado>>>
}