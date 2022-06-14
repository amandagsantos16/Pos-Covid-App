package com.amanda.poscovid.ui.fragment.saude

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.fragment.navArgs
import com.amanda.poscovid.R
import com.amanda.poscovid.databinding.FragmentSelecionaHorarioBinding
import com.amanda.poscovid.extension.*
import com.amanda.poscovid.modelo.Horario
import com.amanda.poscovid.ui.adapter.list.SelecionaHorarioAdapter
import com.amanda.poscovid.ui.fragment.BaseAppFragment
import com.amanda.poscovid.ui.viewModel.SelecionaHorarioViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*

class SelecionaHorarioFragment : BaseAppFragment() {

    private val args by navArgs<SelecionaHorarioFragmentArgs>()
    private lateinit var binding: FragmentSelecionaHorarioBinding
    private val adapter by lazy { SelecionaHorarioAdapter(context) }
    private val progressDialog by lazy { ProgressDialog(context, ProgressDialog.STYLE_HORIZONTAL) }
    private val viewModel by viewModel<SelecionaHorarioViewModel>()
    private val calendar = Calendar.getInstance()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        (activity as AppCompatActivity).supportActionBar?.show()
        binding = FragmentSelecionaHorarioBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configuraAdapter()
        configuraEditText()
        configuraProgress()
        getHorarios()
    }

    private fun configuraAdapter() {
        binding.selecionaHorarioRecyclerView.adapter = adapter
        adapter.clickListener = {
            if (args.agendamento == null) {
                agendar(it)
            } else {
                alterarAgendamento(it)
            }
        }
    }

    private fun alterarAgendamento(horario: Horario) {
        viewModel.alterarAgendamento(args.agendamento!!, horario, calendar.time.formatToApi(), args.isPsicologo).observe(viewLifecycleOwner) {
            it?.apply {
                detalhes?.let {
                    mostrarAlerta(detalhes.error ?: String())
                } ?: mostrarAlerta("Agendamento alterado com sucesso. Aguarde a confirmação") { navController.popBackStack() }
            } ?: mostrarAlerta(getString(R.string.erro_padrao_api))
        }
    }

    private fun agendar(horario: Horario) {
        viewModel.agendarHorario(args.psicologo, horario, calendar.time.formatToApi()).observe(viewLifecycleOwner) {
            it?.apply {
                dados?.let {
                    mostrarAlerta("Agendamento cadastrado com sucesso. Aguarde a confirmação") { navController.popBackStack() }
                }
                detalhes?.let {
                    mostrarAlerta(detalhes.error ?: String())
                }
            } ?: mostrarAlerta(getString(R.string.erro_padrao_api))
        }
    }

    private fun getHorarios() {
        viewModel.getHorariosCadastrados(calendar.time.formatToApi(), args.psicologo.id ?: "").observe(viewLifecycleOwner) {
            it?.apply {
                dados?.let {
                    atualizaAdapter(it)
                }
                detalhes?.let {
                    mostrarAlerta(detalhes.error ?: String()) { navController.popBackStack() }
                }
            } ?: mostrarAlerta(getString(R.string.erro_padrao_api)) { navController.popBackStack() }
        }
    }

    private fun atualizaAdapter(list: List<Horario>) {
        adapter.atualizaLista(list)
        binding.selecionaHorarioEmpty.isVisible = list.isEmpty()
    }

    private fun configuraEditText() {
        setEditText()
        binding.itemSelecionaHorarioEditText.setOnClickListener {
            showDataPickerDialog(it.context, calendar) { _, year, month, day ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, month)
                calendar.set(Calendar.DAY_OF_MONTH, day)
                setEditText()
                getHorarios()
            }
        }
    }

    private fun setEditText() {
        binding.itemSelecionaHorarioEditText.setText(calendar.time.formatToUi())
    }

    private fun configuraProgress() {
        viewModel.isLoading.observe(viewLifecycleOwner) { loading ->
            if (loading)
                progressDialog.show()
            else
                progressDialog.hide()
        }
    }
}