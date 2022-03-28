package com.amanda.poscovid.ui.fragment.login

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import com.amanda.poscovid.databinding.FragmentAlterarSenhaBinding
import com.amanda.poscovid.ui.fragment.BaseAppFragment

class AlterarSenhaFragment : BaseAppFragment() {

    private lateinit var binding: FragmentAlterarSenhaBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentAlterarSenhaBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configuraEditTexts()
        configuraBotaoMostrarSenha()
    }

    private fun configuraBotaoMostrarSenha() {
        configuraSenhaEditText(binding.alterarSenhaConfirmar, binding.alterarSenhaMostraSenha)
        configuraSenhaEditText(binding.alterarSenhaSenha, binding.alterarSenhaConfirmarMostraSenha)
    }

    private fun configuraEditTexts() {
        binding.alterarSenhaConfirmar.addTextChangedListener(afterTextChanged = editTextChanged)
        binding.alterarSenhaSenha.addTextChangedListener(afterTextChanged = editTextChanged)
        binding.alterarSenhaCodigo.addTextChangedListener(afterTextChanged = editTextChanged)
    }

    private val editTextChanged: (Editable?) -> Unit = { _ ->
        validaBotao()
    }

    private fun validaBotao() {
        binding.alterarSenhaButton.isEnabled = binding.alterarSenhaConfirmar.text.toString().isNotEmpty() &&
                binding.alterarSenhaCodigo.text.toString().isNotEmpty() &&
                binding.alterarSenhaSenha.text.toString().isNotEmpty()
    }
}