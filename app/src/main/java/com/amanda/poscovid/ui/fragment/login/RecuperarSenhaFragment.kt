package com.amanda.poscovid.ui.fragment.login

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import com.amanda.poscovid.databinding.FragmentRecuperarSenhaBinding
import com.amanda.poscovid.ui.fragment.BaseAppFragment

class RecuperarSenhaFragment : BaseAppFragment() {

    private lateinit var binding: FragmentRecuperarSenhaBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentRecuperarSenhaBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
        configuraEditTexts()
    }

    private fun setListeners() {
        binding.recuperarSenhaButton.setOnClickListener {
            navigateTo(RecuperarSenhaFragmentDirections.recuperarSenhaParaAlterarSenha())
        }
    }

    private fun configuraEditTexts() {
        binding.recuperarSenhaEmail.addTextChangedListener(afterTextChanged = editTextChanged)
    }

    private val editTextChanged: (Editable?) -> Unit = { _ ->
        validaBotaoCadastrar()
    }

    private fun validaBotaoCadastrar() {
        binding.recuperarSenhaButton.isEnabled = binding.recuperarSenhaEmail.text.toString().isNotEmpty()
    }
}