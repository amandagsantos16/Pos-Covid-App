package com.amanda.poscovid.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.amanda.poscovid.api.client.PacienteWebClient
import com.amanda.poscovid.api.modelo.RespostaWebClient
import com.amanda.poscovid.modelo.Horario

class SelecionaHorarioViewModel(private val client: PacienteWebClient) : ViewModel() {

    val isLoading = MutableLiveData<Boolean>().also {
        it.value = false
    }

    fun getHorariosCadastrados(dia: String, psicologoId: String): LiveData<RespostaWebClient<List<Horario>>?> {
        val liveData = MutableLiveData<RespostaWebClient<List<Horario>>?>()
        isLoading.value = true
        client.getHorariosDisponiveis(dia, psicologoId) { resposta ->
            isLoading.postValue(false)
            liveData.postValue(resposta)
        }
        return liveData
    }
}