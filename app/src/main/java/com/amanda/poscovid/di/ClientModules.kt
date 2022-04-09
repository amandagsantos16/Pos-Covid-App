package com.amanda.poscovid.di

import com.amanda.poscovid.api.client.CovidWebClient
import org.koin.dsl.module

val clientModules = module {
    single<CovidWebClient> { CovidWebClient() }
}