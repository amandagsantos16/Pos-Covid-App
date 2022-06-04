package com.amanda.poscovid.database

import androidx.lifecycle.MutableLiveData
import com.amanda.poscovid.modelo.Noticia

class NoticiaHelper(private val helper: FirebaseHelper) {

    private val noticias = MutableLiveData<List<Noticia>?>()

    fun getNoticiasAtualizadas(): MutableLiveData<List<Noticia>?> {
        atualizaAssinantes()
        return noticias
    }

    private fun atualizaAssinantes() {
        helper.getNoticias(
            quandoAtualiza = {
                val toObjects = it.toObjects(Noticia::class.java)
                noticias.value = toObjects
            })
    }
}