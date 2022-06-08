package com.amanda.poscovid.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.amanda.poscovid.api.client.UsuarioWebClient
import com.amanda.poscovid.api.modelo.LoginRetorno
import com.amanda.poscovid.api.modelo.RespostaWebClient
import com.amanda.poscovid.modelo.NovaConta
import com.amanda.poscovid.preferences.ITokenPreferenceHelper
import com.amanda.poscovid.preferences.IUserPreferenceHelper

class LoginViewModel(
    private val client: UsuarioWebClient,
    private val tokenHelper: ITokenPreferenceHelper,
    private val userHelper: IUserPreferenceHelper
) : ViewModel() {

    val isLoading = MutableLiveData<Boolean>().also {
        it.value = false
    }

    fun cadastraConta(conta: NovaConta): LiveData<RespostaWebClient<LoginRetorno>?> {
        isLoading.value = true
        val liveData = MutableLiveData<RespostaWebClient<LoginRetorno>?>()
        client.cadastrarNovoUsuario(conta) { resposta ->
            resposta?.dados?.let { loginRetorno ->
                loginRetorno.accessToken?.let {
                    tokenHelper.accessToken = it
                }
                loginRetorno.usuarioToken?.id?.let {
                    userHelper.id = it
                }
                loginRetorno.usuarioToken?.email?.let {
                    userHelper.email = it
                }
            }
            liveData.postValue(resposta)
            isLoading.postValue(false)
        }

        return liveData
    }
}