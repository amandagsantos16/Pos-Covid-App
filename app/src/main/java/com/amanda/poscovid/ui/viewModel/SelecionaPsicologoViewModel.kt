package com.amanda.poscovid.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.amanda.poscovid.api.client.PacienteWebClient
import com.amanda.poscovid.api.modelo.RespostaWebClient
import com.amanda.poscovid.modelo.Psicologo

class SelecionaPsicologoViewModel(private val client: PacienteWebClient) : ViewModel() {

    val isLoading = MutableLiveData<Boolean>().also {
        it.value = false
    }

    fun getPsicologos() : LiveData<RespostaWebClient<List<Psicologo>>?> {
        val liveData = MutableLiveData<RespostaWebClient<List<Psicologo>>?>()
        isLoading.value = true
        client.getPsicologos { resposta ->
            isLoading.postValue(false)
            liveData.postValue(resposta)
        }
        return liveData
    }
}