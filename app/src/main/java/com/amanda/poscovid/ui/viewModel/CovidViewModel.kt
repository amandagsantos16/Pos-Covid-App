package com.amanda.poscovid.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.amanda.poscovid.api.client.CovidWebClient
import com.amanda.poscovid.api.modelo.RespostaCovid
import com.amanda.poscovid.api.modelo.RespostaWebClient
import com.amanda.poscovid.modelo.Estado

class CovidViewModel(private val client: CovidWebClient) : ViewModel() {

    val isLoading = MutableLiveData<Boolean>().also {
        it.value = false
    }

    fun buscaListaDeEstados(): LiveData<RespostaWebClient<RespostaCovid<List<Estado>>>?> {
        isLoading.value = true
        val liveData = MutableLiveData<RespostaWebClient<RespostaCovid<List<Estado>>>?>()
        client.buscaListaDeEstados {
            liveData.postValue(it)
            isLoading.postValue(false)
        }
        return liveData
    }
}