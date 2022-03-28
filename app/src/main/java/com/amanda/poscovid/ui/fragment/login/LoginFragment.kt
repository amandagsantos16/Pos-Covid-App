package com.amanda.poscovid.ui.fragment.login

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import com.amanda.poscovid.databinding.FragmentLoginBinding
import com.amanda.poscovid.ui.fragment.BaseAppFragment

class LoginFragment : BaseAppFragment() {

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentLoginBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configuraEditTexts()
        configuraBotaoMostrarSenha()
        setListeners()
    }

    private fun setListeners() {
        binding.loginEsqueciSenha.setOnClickListener {
            navigateTo(LoginFragmentDirections.loginParaRecuperarSenha())
        }
    }

    private fun configuraEditTexts() {
        binding.loginCpf.addTextChangedListener(afterTextChanged = editTextChanged)
        binding.loginSenha.addTextChangedListener(afterTextChanged = editTextChanged)
    }

    private fun configuraBotaoMostrarSenha() {
        configuraSenhaEditText(binding.loginSenha, binding.loginMostraSenha)
    }

    private val editTextChanged: (Editable?) -> Unit = { _ ->
        validaBotaoCadastrar()
    }

    private fun validaBotaoCadastrar() {
        binding.loginConfirmar.isEnabled = binding.loginSenha.text.toString().isNotEmpty() &&
                binding.loginCpf.text.toString().isNotEmpty()
    }
}