package com.amanda.poscovid.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.amanda.poscovid.database.NoticiaHelper
import com.amanda.poscovid.modelo.Noticia

class NoticiaViewModel(private val assinanteHelper: NoticiaHelper) : ViewModel()  {

    fun getAssinantes(): MutableLiveData<List<Noticia>?> {
        return assinanteHelper.getNoticiasAtualizadas()
    }
}