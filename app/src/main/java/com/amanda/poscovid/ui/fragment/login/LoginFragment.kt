package com.amanda.poscovid.ui.fragment.login

import android.app.ProgressDialog
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import com.amanda.poscovid.R
import com.amanda.poscovid.databinding.FragmentLoginBinding
import com.amanda.poscovid.modelo.NovaConta
import com.amanda.poscovid.modelo.UsuarioLogin
import com.amanda.poscovid.ui.fragment.BaseAppFragment
import com.amanda.poscovid.ui.viewModel.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : BaseAppFragment() {

    private lateinit var binding: FragmentLoginBinding
    private val viewModel by viewModel<LoginViewModel>()
    private val progressDialog by lazy { ProgressDialog(context, ProgressDialog.STYLE_HORIZONTAL) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentLoginBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configuraEditTexts()
        configuraBotaoMostrarSenha()
        setListeners()
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
        binding.loginConfirmar.setOnClickListener {
            viewModel.fazerLogin(instanciaConta()).observe(viewLifecycleOwner) {
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

    private fun instanciaConta(): UsuarioLogin {
        return UsuarioLogin(binding.loginCpf.text.toString(), binding.loginSenha.text.toString())
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