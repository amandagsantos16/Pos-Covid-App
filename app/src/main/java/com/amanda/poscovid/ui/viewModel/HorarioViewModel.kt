package com.amanda.poscovid.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.amanda.poscovid.api.client.PsicologoWebClient
import com.amanda.poscovid.api.modelo.RespostaWebClient
import com.amanda.poscovid.modelo.Horario

class HorarioViewModel(private val client: PsicologoWebClient) : ViewModel() {

    val isLoading = MutableLiveData<Boolean>().also {
        it.value = false
    }

    fun getHorariosCadastrados(diaSemana: Int): LiveData<RespostaWebClient<List<Horario>>?> {
        val liveData = MutableLiveData<RespostaWebClient<List<Horario>>?>()
        isLoading.value = true
        client.getHorariosCadastrados(diaSemana) { resposta ->
            isLoading.postValue(false)
            liveData.postValue(resposta)
        }
        return liveData
    }
}