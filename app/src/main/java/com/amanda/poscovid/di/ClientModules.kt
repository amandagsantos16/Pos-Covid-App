package com.amanda.poscovid.di

import com.amanda.poscovid.api.client.CovidWebClient
import com.amanda.poscovid.api.client.PacienteWebClient
import com.amanda.poscovid.api.client.PsicologoWebClient
import com.amanda.poscovid.api.client.UsuarioWebClient
import org.koin.dsl.module

val clientModules = module {
    single<CovidWebClient> { CovidWebClient() }
    single<UsuarioWebClient> { UsuarioWebClient(get()) }
    single<PsicologoWebClient> { PsicologoWebClient(get(), get()) }
    single<PacienteWebClient> { PacienteWebClient(get(), get()) }
}