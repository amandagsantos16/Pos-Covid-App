package com.amanda.poscovid.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.amanda.poscovid.api.client.PacienteWebClient
import com.amanda.poscovid.api.modelo.RespostaWebClient
import com.amanda.poscovid.modelo.Agendamento

class AgendaPacienteViewModel(private val client: PacienteWebClient) : ViewModel() {

    val isLoading = MutableLiveData<Boolean>().also {
        it.value = false
    }

    fun getAgendamentos() : LiveData<RespostaWebClient<List<Agendamento>>?> {
        val liveData = MutableLiveData<RespostaWebClient<List<Agendamento>>?>()
        isLoading.value = true
        client.getAgendamentos { resposta ->
            isLoading.postValue(false)
            liveData.postValue(resposta)
        }
        return liveData
    }
}