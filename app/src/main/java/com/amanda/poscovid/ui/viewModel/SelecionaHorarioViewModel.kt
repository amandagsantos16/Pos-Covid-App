package com.amanda.poscovid.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.amanda.poscovid.api.client.PacienteWebClient
import com.amanda.poscovid.api.client.PsicologoWebClient
import com.amanda.poscovid.api.modelo.RespostaWebClient
import com.amanda.poscovid.modelo.Agendamento
import com.amanda.poscovid.modelo.Horario
import com.amanda.poscovid.modelo.Psicologo

class SelecionaHorarioViewModel(private val pacienteClient: PacienteWebClient, private val psicologoClient: PsicologoWebClient) : ViewModel() {

    val isLoading = MutableLiveData<Boolean>().also {
        it.value = false
    }

    fun getHorariosCadastrados(dia: String, psicologoId: String): LiveData<RespostaWebClient<List<Horario>>?> {
        val liveData = MutableLiveData<RespostaWebClient<List<Horario>>?>()
        isLoading.value = true
        pacienteClient.getHorariosDisponiveis(dia, psicologoId) { resposta ->
            isLoading.postValue(false)
            liveData.postValue(resposta)
        }
        return liveData
    }

    fun agendarHorario(psicologo: Psicologo, horario: Horario, data: String): LiveData<RespostaWebClient<Any>?> {
        val liveData = MutableLiveData<RespostaWebClient<Any>?>()
        isLoading.value = true
        pacienteClient.agendarHorario(psicologo, horario, data) { resposta ->
            isLoading.postValue(false)
            liveData.postValue(resposta)
        }
        return liveData
    }

    fun alterarAgendamento(agendamento: Agendamento, horario: Horario, data: String, isPsicologo: Boolean): LiveData<RespostaWebClient<Void>?> {
        val liveData = MutableLiveData<RespostaWebClient<Void>?>()
        isLoading.value = true
        val retorno: (RespostaWebClient<Void>?) -> Unit = { resposta ->
            isLoading.postValue(false)
            liveData.postValue(resposta)
        }
        if (isPsicologo) psicologoClient.alterarAgendamento(agendamento, horario, data, retorno)
        else pacienteClient.alterarAgendamento(agendamento, horario, data, retorno)
        return liveData
    }
}