package com.amanda.poscovid.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.amanda.poscovid.api.client.UsuarioWebClient
import com.amanda.poscovid.api.modelo.LoginRetorno
import com.amanda.poscovid.api.modelo.RespostaWebClient
import com.amanda.poscovid.modelo.NovaConta
import com.amanda.poscovid.modelo.UsuarioLogin
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
        val liveData = MutableLiveData<RespostaWebClient<LoginRetorno>?>()
        isLoading.value = true
        client.cadastrarNovoUsuario(conta) { resposta ->
            isLoading.postValue(false)
            resposta?.dados?.let { loginRetorno ->
                loginRetorno.accessToken?.let {
                    tokenHelper.accessToken = it
                }
                loginRetorno.usuarioToken?.id?.let {
                    userHelper.id = it
                }
                loginRetorno.usuarioToken?.pacienteId?.let {
                    userHelper.pacienteId = it
                }
                loginRetorno.usuarioToken?.psicologoId?.let {
                    userHelper.psicologoId = it
                }
                loginRetorno.usuarioToken?.email?.let {
                    userHelper.email = it
                }
            }
            liveData.postValue(resposta)
        }

        return liveData
    }

    fun fazerLogin(login: UsuarioLogin): LiveData<RespostaWebClient<LoginRetorno>?> {
        val liveData = MutableLiveData<RespostaWebClient<LoginRetorno>?>()
        isLoading.value = true
        client.iniciaSessao(login) { resposta ->
            isLoading.postValue(false)
            resposta?.dados?.let { loginRetorno ->
                loginRetorno.accessToken?.let {
                    tokenHelper.accessToken = it
                }
                loginRetorno.usuarioToken?.id?.let {
                    userHelper.id = it
                }
                loginRetorno.usuarioToken?.pacienteId?.let {
                    userHelper.pacienteId = it
                }
                loginRetorno.usuarioToken?.psicologoId?.let {
                    userHelper.psicologoId = it
                }
                loginRetorno.usuarioToken?.email?.let {
                    userHelper.email = it
                }
            }
            liveData.postValue(resposta)
        }

        return liveData
    }
}