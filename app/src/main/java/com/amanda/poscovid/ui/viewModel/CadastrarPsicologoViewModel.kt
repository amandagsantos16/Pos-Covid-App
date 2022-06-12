package com.amanda.poscovid.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.amanda.poscovid.api.client.PsicologoWebClient
import com.amanda.poscovid.api.modelo.RespostaWebClient
import com.amanda.poscovid.modelo.CadastrarPsicologo
import com.amanda.poscovid.modelo.Psicologo
import com.squareup.okhttp.ResponseBody

class CadastrarPsicologoViewModel(private val client: PsicologoWebClient) : ViewModel() {

    val isLoading = MutableLiveData<Boolean>().also {
        it.value = false
    }

    fun cadastrarPsicologo(psicolog: CadastrarPsicologo) : LiveData<RespostaWebClient<Psicologo>?> {
        val liveData = MutableLiveData<RespostaWebClient<Psicologo>?>()
        isLoading.value = true
        client.cadastrarPsicologo(psicolog) { resposta ->
            isLoading.postValue(false)
            liveData.postValue(resposta)
        }
        return liveData
    }

}