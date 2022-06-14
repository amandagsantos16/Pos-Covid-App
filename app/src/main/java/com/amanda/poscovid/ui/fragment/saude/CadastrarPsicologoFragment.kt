package com.amanda.poscovid.ui.fragment.saude

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.amanda.poscovid.R
import com.amanda.poscovid.databinding.FragmentCadastrarPsicologoBinding
import com.amanda.poscovid.modelo.CadastrarPsicologo
import com.amanda.poscovid.ui.fragment.BaseAppFragment
import com.amanda.poscovid.ui.viewModel.CadastrarPsicologoViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CadastrarPsicologoFragment : BaseAppFragment() {

    private lateinit var binding: FragmentCadastrarPsicologoBinding
    private val viewModel by viewModel<CadastrarPsicologoViewModel>()
    private val progressDialog by lazy { ProgressDialog(context, ProgressDialog.STYLE_HORIZONTAL) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        (activity as AppCompatActivity).supportActionBar?.show()
        binding = FragmentCadastrarPsicologoBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configuraEditTexts()
        confguraBotaoEnviar()
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

    private fun confguraBotaoEnviar() {
        binding.cadastrarPsicologoConfirmar.setOnClickListener {
            getPsicologo()
            viewModel.cadastrarPsicologo(getPsicologo()).observe(viewLifecycleOwner) {
                it?.apply {
                    dados?.let {
                        mostrarAlerta(getString(R.string.cadastrar_psicologo_dados_enviados)) {
                            navController.popBackStack()
                        }
                    }
                    detalhes?.let {
                        mostrarAlerta(detalhes.error ?: String())
                    }
                } ?: mostrarAlerta(getString(R.string.erro_padrao_api))
            }
        }
    }

    private fun getPsicologo(): CadastrarPsicologo {
        val trim = binding.cadastrarPsicologoNascimento.text.toString().split("/")
        val nascimento = "${trim[2]}/${trim[1]}/${trim[0]}"
        return CadastrarPsicologo().also {
            it.crp = binding.cadastrarPsicologoCrp.text.toString()
            it.especializacoes = binding.cadastrarPsicologoEspecializacao.text.toString()
            it.dataNascimento = nascimento
            it.nome = binding.cadastrarPsicologoNome.text.toString()
            it.resumo = binding.cadastrarPsicologoResumo.text.toString()
        }
    }

    private fun configuraEditTexts() {
        binding.cadastrarPsicologoCrp.addTextChangedListener(afterTextChanged = { validaBotaoCadastrar() })
        binding.cadastrarPsicologoEspecializacao.addTextChangedListener(afterTextChanged = { validaBotaoCadastrar() })
        binding.cadastrarPsicologoNascimento.addTextChangedListener(afterTextChanged = { validaBotaoCadastrar() })
        binding.cadastrarPsicologoNome.addTextChangedListener(afterTextChanged = { validaBotaoCadastrar() })
        binding.cadastrarPsicologoResumo.addTextChangedListener(afterTextChanged = { validaBotaoCadastrar() })
    }

    private fun validaBotaoCadastrar() {
        binding.cadastrarPsicologoConfirmar.isEnabled = binding.cadastrarPsicologoCrp.text.toString().isNotEmpty() &&
                binding.cadastrarPsicologoEspecializacao.text.toString().isNotEmpty() &&
                binding.cadastrarPsicologoNome.text.toString().isNotEmpty() &&
                binding.cadastrarPsicologoResumo.text.toString().isNotEmpty() &&
                binding.cadastrarPsicologoNascimento.text.toString().isNotEmpty()
    }
}