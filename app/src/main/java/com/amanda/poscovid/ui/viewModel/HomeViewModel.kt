package com.amanda.poscovid.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.amanda.poscovid.api.client.UsuarioWebClient
import com.amanda.poscovid.modelo.Notificacao
import com.amanda.poscovid.preferences.ITokenPreferenceHelper

class HomeViewModel(private val client: UsuarioWebClient, private val preferences: ITokenPreferenceHelper) : ViewModel() {

    val notificacoes = MutableLiveData<List<Notificacao>?>()

    val temNotificacao = MutableLiveData<Boolean>().also { it.value = false }

    fun atualizaNotificacoes() {
        client.buscaNotificacoes { resposta ->
            resposta?.dados?.let {
                notificacoes.postValue(it)
                temNotificacao.value = it.size > preferences.notificacoes
            }
        }
    }

    fun notificacoesLidas() {
        temNotificacao.postValue(false)
        preferences.notificacoes = notificacoes.value?.size ?: 0
    }

}