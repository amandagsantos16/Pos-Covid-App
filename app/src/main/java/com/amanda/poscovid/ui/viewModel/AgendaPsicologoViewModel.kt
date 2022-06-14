package com.amanda.poscovid.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.amanda.poscovid.api.client.PsicologoWebClient
import com.amanda.poscovid.api.modelo.RespostaWebClient
import com.amanda.poscovid.modelo.Agendamento

class AgendaPsicologoViewModel(private val client: PsicologoWebClient) : ViewModel() {

    val isLoading = MutableLiveData<Boolean>().also {
        it.value = false
    }

    fun getAgendamentos(): LiveData<RespostaWebClient<List<Agendamento>>?> {
        val liveData = MutableLiveData<RespostaWebClient<List<Agendamento>>?>()
        isLoading.value = true
        client.getAgendamentos { resposta ->
            isLoading.postValue(false)
            liveData.postValue(resposta)
        }
        return liveData
    }

    fun cancelarAgendamento(agendamentoId: String): LiveData<RespostaWebClient<Void>?> {
        val liveData = MutableLiveData<RespostaWebClient<Void>?>()
        isLoading.value = true
        client.deletarAgendamento(agendamentoId) { resposta ->
            isLoading.postValue(false)
            liveData.postValue(resposta)
        }
        return liveData
    }

    fun confirmar(agendamento: Agendamento): LiveData<RespostaWebClient<Void>?> {
        val liveData = MutableLiveData<RespostaWebClient<Void>?>()
        isLoading.value = true
        client.confrmarAgendamento(agendamento.id ?: "") { resposta ->
            isLoading.postValue(false)
            liveData.postValue(resposta)
        }
        return liveData
    }
}