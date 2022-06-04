package com.amanda.poscovid.database

import com.google.firebase.firestore.*

class FirebaseHelper(private val db: FirebaseFirestore) {

    init {
        val settings = FirebaseFirestoreSettings.Builder().setPersistenceEnabled(true).build()
        db.firestoreSettings = settings
    }

    fun getNoticias(quandoAtualiza: (QuerySnapshot) -> Unit) {
        db.collection("noticias")
            .addSnapshotListener { snapshot, e ->
                if (e != null) {
                    return@addSnapshotListener
                }

                snapshot?.let {
                    quandoAtualiza(it)
                }
            }
    }
}