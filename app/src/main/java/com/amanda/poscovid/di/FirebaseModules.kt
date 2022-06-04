package com.amanda.poscovid.di

import com.amanda.poscovid.database.FirebaseHelper
import com.amanda.poscovid.database.NoticiaHelper
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.koin.dsl.module

val FirebaseModules = module {
    single<FirebaseHelper> { FirebaseHelper(Firebase.firestore) }
    single<NoticiaHelper> { NoticiaHelper(get()) }
}