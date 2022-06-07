package com.amanda.poscovid.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.amanda.poscovid.api.client.UsuarioWebClient
import com.amanda.poscovid.api.modelo.RespostaWebClient
import com.amanda.poscovid.modelo.NovaConta
import com.amanda.poscovid.preferences.ITokenPreferenceHelper
import com.amanda.poscovid.preferences.IUserPreferenceHelper

class LoginViewModel(
    private val client: UsuarioWebClient,
    private val tokenHelper: ITokenPreferenceHelper,
    private val userHelper: IUserPreferenceHelper) : ViewModel() {

    val isLoading = MutableLiveData<Boolean>().also {
        it.value = false
    }

    fun cadastraConta(conta: NovaConta): LiveData<RespostaWebClient<Boolean>?> {
        isLoading.value = true
        val liveData = MutableLiveData<RespostaWebClient<Boolean>?>()
        client.cadastrarNovoUsuario(conta) {
            isLoading.postValue(false)
        }

        return liveData
    }
}