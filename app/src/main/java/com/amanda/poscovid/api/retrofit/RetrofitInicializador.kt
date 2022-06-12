package com.amanda.poscovid.api.retrofit

import com.amanda.poscovid.api.retrofit.RetrofitUtil.getUnsafeOkHttpClient
import com.amanda.poscovid.api.service.CovidService
import com.amanda.poscovid.api.service.LoginService
import com.amanda.poscovid.api.service.PacienteService
import com.amanda.poscovid.api.service.PsicologoService
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import java.util.concurrent.TimeUnit

@JsonIgnoreProperties(ignoreUnknown = true)
class RetrofitInicializador {

    private val client by lazy {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BASIC
        OkHttpClient.Builder()
            .addInterceptor(interceptor) //o loggin tem que ser o ultimo a ser adicionado
            .connectTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://192.168.1.117:7264/")
            .addConverterFactory(JacksonConverterFactory.create())
            .client(getUnsafeOkHttpClient().build())
            .build()
    }

    private val retrofitCovid: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://covid19-brazil-api.now.sh/")
            .addConverterFactory(JacksonConverterFactory.create())
            .client(client)
            .build()
    }

    val covidService by lazy { retrofitCovid.create(CovidService::class.java) }

    val loginService by lazy { retrofit.create(LoginService::class.java) }

    val psicologoService by lazy { retrofit.create(PsicologoService::class.java) }

    val pacienteService by lazy { retrofit.create(PacienteService::class.java) }
}