package com.amanda.poscovid.ui.fragment.login

import android.app.ProgressDialog
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import com.amanda.poscovid.R
import com.amanda.poscovid.databinding.FragmentCadastrarLoginBinding
import com.amanda.poscovid.modelo.NovaConta
import com.amanda.poscovid.ui.fragment.BaseAppFragment
import com.amanda.poscovid.ui.viewModel.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CadastrarLoginFragment : BaseAppFragment() {

    private lateinit var binding: FragmentCadastrarLoginBinding
    private val viewModel by viewModel<LoginViewModel>()
    private val progressDialog by lazy { ProgressDialog(context, ProgressDialog.STYLE_HORIZONTAL) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentCadastrarLoginBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configuraEditTexts()
        configuraBotaoMostrarSenha()
        configuraBotaoCadastrar()
        configuraProgress()
    }

    private fun configuraProgress() {
        viewModel.isLoading.observe(viewLifecycleOwner) { loading ->
            if (loading)
                progressDialog.show()
            else
                progressDialog.hide()
        }
    }

    private fun configuraBotaoCadastrar() {
        binding.cadasrarConfirmar.setOnClickListener {
            viewModel.cadastraConta(instanciaConta()).observe(viewLifecycleOwner) {
                it?.apply {
                    dados?.let {
                        activity?.finish()
                    }
                    detalhes?.let {
                        mostrarAlerta(detalhes.error ?: String())
                    }
                } ?: mostrarAlerta(getString(R.string.erro_padrao_api))
            }
        }
    }

    private fun configuraBotaoMostrarSenha() {
        configuraSenhaEditText(binding.cadastrarLoginSenha, binding.cadastrarLoginMostraSenha)
        configuraSenhaEditText(binding.cadastrarLoginSenhaConfirmar, binding.cadastrarLoginMostraSenhaConfirmar)
    }

    private fun configuraEditTexts() {
        binding.cadastrarLoginSenhaConfirmar.addTextChangedListener(afterTextChanged = editTextChanged)
        binding.cadastrarLoginEmail.addTextChangedListener(afterTextChanged = editTextChanged)
        binding.cadastrarLoginSenha.addTextChangedListener(afterTextChanged = editTextChanged)
    }

    private val editTextChanged: (Editable?) -> Unit = { _ ->
        validaBotaoCadastrar()
    }

    private fun validaBotaoCadastrar() {
        binding.cadasrarConfirmar.isEnabled = binding.cadastrarLoginSenhaConfirmar.text.toString().isNotEmpty() &&
                binding.cadastrarLoginEmail.text.toString().isNotEmpty() &&
                binding.cadastrarLoginSenha.text.toString().isNotEmpty()
    }

    private fun instanciaConta(): NovaConta {
        return NovaConta().also {
            it.email = binding.cadastrarLoginEmail.text.toString()
            it.senha = binding.cadastrarLoginSenha.text.toString()
            it.senhaConfirmacao = binding.cadastrarLoginSenhaConfirmar.text.toString()
        }
    }
}