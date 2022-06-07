package com.amanda.poscovid.di

import com.amanda.poscovid.preferences.ITokenPreferenceHelper
import com.amanda.poscovid.preferences.IUserPreferenceHelper
import com.amanda.poscovid.preferences.TokenPreferenceManager
import com.amanda.poscovid.preferences.UserPreferenceManager
import org.koin.dsl.module

val preferencesModule = module {
    single<IUserPreferenceHelper> { UserPreferenceManager(get()) }
    single<ITokenPreferenceHelper> { TokenPreferenceManager(get()) }
}