package com.amanda.poscovid.ui.fragment.login

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import com.amanda.poscovid.databinding.FragmentCadastrarLoginBinding
import com.amanda.poscovid.ui.fragment.BaseAppFragment

class CadastrarLoginFragment : BaseAppFragment() {

    private lateinit var binding: FragmentCadastrarLoginBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentCadastrarLoginBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configuraEditTexts()
        configuraBotaoMostrarSenha()
    }

    private fun configuraBotaoMostrarSenha() {
        configuraSenhaEditText(binding.cadastrarLoginSenha, binding.cadastrarLoginMostraSenha)
    }

    private fun configuraEditTexts() {
        binding.cadastrarLoginNome.addTextChangedListener(afterTextChanged = editTextChanged)
        binding.cadastrarLoginEmail.addTextChangedListener(afterTextChanged = editTextChanged)
        binding.cadastrarLoginSenha.addTextChangedListener(afterTextChanged = editTextChanged)
    }

    private val editTextChanged: (Editable?) -> Unit = { _ ->
        validaBotaoCadastrar()
    }

    private fun validaBotaoCadastrar() {
        binding.cadasrarConfirmar.isEnabled = binding.cadastrarLoginNome.text.toString().isNotEmpty() &&
                binding.cadastrarLoginEmail.text.toString().isNotEmpty() &&
                binding.cadastrarLoginSenha.text.toString().isNotEmpty()
    }
}