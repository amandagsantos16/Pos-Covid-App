package com.amanda.poscovid.api.client

import com.amanda.poscovid.api.modelo.DetalheWebClient
import com.amanda.poscovid.api.modelo.RespostaWebClient
import com.amanda.poscovid.api.retrofit.RetrofitInicializador
import com.amanda.poscovid.preferences.ITokenPreferenceHelper
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class WebClient(private val manager : ITokenPreferenceHelper? = null) {

    val retrofit = RetrofitInicializador()

    fun <T> Call<T>.executaRequest(resposta: (RespostaWebClient<T>?) -> Unit) {
        enqueue(object : Callback<T> {
            override fun onFailure(call: Call<T>, t: Throwable) {
                resposta(null)
            }

            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.isSuccessful) {
                    resposta(RespostaWebClient(response.body()))
                } else {
                    retornaMensagemDeErro(response, resposta)
                }
            }
        })
    }

    private fun <T> retornaMensagemDeErro(response: Response<T>, resposta: (RespostaWebClient<T>?) -> Unit) {
        try {
            val jsonObject = JSONObject(response.errorBody()?.string() ?: "")
            val detalheWebClient = DetalheWebClient("", "", jsonObject.optString("title"))
            resposta(RespostaWebClient(null, detalheWebClient))
        } catch (e: Exception) {
            resposta(null)
        }
    }

    fun bearerToken(): String {
        return "bearer ${manager?.accessToken}"
    }
}
