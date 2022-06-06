package com.amanda.poscovid.ui.fragment.saude

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.amanda.poscovid.R
import com.amanda.poscovid.databinding.FragmentCadastrarPsicologoBinding
import com.amanda.poscovid.ui.fragment.BaseAppFragment

class CadastrarPsicologoFragment : BaseAppFragment() {

    private lateinit var binding: FragmentCadastrarPsicologoBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        (activity as AppCompatActivity).supportActionBar?.show()
        binding = FragmentCadastrarPsicologoBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configuraEditTexts()
        confguraBotaoEnviar()
    }

    private fun confguraBotaoEnviar() {
        binding.cadastrarPsicologoConfirmar.setOnClickListener {
            mostrarAlerta(getString(R.string.cadastrar_psicologo_dados_enviados)) {
                navController.popBackStack()
            }
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