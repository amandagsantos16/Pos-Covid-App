package com.amanda.poscovid.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.amanda.poscovid.api.client.UsuarioWebClient

class HomeViewModel(private val client: UsuarioWebClient) : ViewModel() {

    val notificacoes = MutableLiveData<List<Any>?>()

    val temNotificacao = MutableLiveData<Boolean>().also { it.value = false }

    fun atualizaNotificacoes() {
        client.buscaNotificacoes { resposta ->
            resposta?.dados?.let {
                notificacoes.postValue(it)
            }
        }
    }

}