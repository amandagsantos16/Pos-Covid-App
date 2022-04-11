package com.amanda.poscovid.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.amanda.poscovid.R
import com.amanda.poscovid.api.client.CovidWebClient
import com.amanda.poscovid.api.modelo.RespostaCovid
import com.amanda.poscovid.api.modelo.RespostaWebClient
import com.amanda.poscovid.modelo.Estado
import com.amanda.poscovid.modelo.Noticia

class CovidViewModel(private val client: CovidWebClient) : ViewModel() {

    val noticias= MutableLiveData<List<Noticia>>().also { noticias ->
        noticias.value = listOf(
            Noticia().also {
                it.titulo = "Xangai adota lockdown rígido por causa da Covid, e 25 milhões de pessoas são mantidas em casa"
                it.imagem = R.drawable.xangai
            },
            Noticia().also {
                it.titulo = "Covid: Brasil tem 1º caso da ômicron XE, que pode ser mais transmissível"
                it.imagem = R.drawable.omicron
            },
            Noticia().also {
                it.titulo = "O que aconteceu nos países que acabaram com as restrições contra a covid"
                it.imagem = R.drawable.fim_restricao
            }
        )
    }
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