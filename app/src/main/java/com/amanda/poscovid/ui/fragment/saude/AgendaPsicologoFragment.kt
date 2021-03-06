package com.amanda.poscovid.ui.fragment.saude

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.amanda.poscovid.R
import com.amanda.poscovid.databinding.FragmentAgendaPacienteBinding
import com.amanda.poscovid.modelo.Agendamento
import com.amanda.poscovid.ui.adapter.list.AgendaAdapter
import com.amanda.poscovid.ui.fragment.BaseAppFragment
import com.amanda.poscovid.ui.viewModel.AgendaPsicologoViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AgendaPsicologoFragment : BaseAppFragment() {

    private lateinit var binding: FragmentAgendaPacienteBinding
    private val viewModel by viewModel<AgendaPsicologoViewModel>()
    private val adapter by lazy { AgendaAdapter(context, true) }
    private val progressDialog by lazy { ProgressDialog(context, ProgressDialog.STYLE_HORIZONTAL) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        (activity as AppCompatActivity).supportActionBar?.show()
        binding = FragmentAgendaPacienteBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configuraAdapter()
        configuraFab()
        configuraProgress()
        atualizaLista()
    }

    private fun atualizaLista() {
        viewModel.getAgendamentos().observe(viewLifecycleOwner) {
            it?.apply {
                dados?.let {
                    atalizaAdapter(it)
                }
                detalhes?.let {
                    mostrarAlerta(detalhes.error ?: String()) { navController.popBackStack() }
                }
            } ?: mostrarAlerta(getString(R.string.erro_padrao_api)) { navController.popBackStack() }
        }
    }

    private fun atalizaAdapter(lista: List<Agendamento>) {
        adapter.atualizaLista(lista)
        binding.agendaPacienteEmpty.isVisible = lista.isEmpty()
    }

    private fun configuraAdapter() {
        binding.agendaPacienteRecyclerView.adapter = adapter
        adapter.deleteListener = { agendamento ->
            context?.let {
                AlertDialog.Builder(it)
                    .setCancelable(false)
                    .setMessage("Deseja realmente cancelar esta consulta?")
                    .setPositiveButton("Sim, cancelar") { _, _ ->
                        cancelarAgendamento(agendamento)
                    }
                    .setNegativeButton("N??o") { _, _ ->
                    }
                    .create().show()
            }
        }
        adapter.editListener = {
            editarHorario(it)
        }
        adapter.confirmarListener = {
            confirmar(it)
        }
    }

    private fun cancelarAgendamento(agendamento: Agendamento) {
        viewModel.cancelarAgendamento(agendamento.id ?: "").observe(viewLifecycleOwner) {
            it?.apply {
                detalhes?.let {
                    mostrarAlerta(detalhes.error ?: String())
                } ?: atualizaLista()
            } ?: mostrarAlerta(getString(R.string.erro_padrao_api))
        }
    }

    private fun editarHorario(agendamento: Agendamento) {
        navigateTo(
            AgendaPsicologoFragmentDirections.actionAgendaPsicologoFragmentToSelecionaHorario(agendamento.psicologo!!, agendamento, true)
        )
    }

    private fun confirmar(agendamento: Agendamento) {
        viewModel.confirmar(agendamento).observe(viewLifecycleOwner) {
            it?.apply {
                detalhes?.let {
                    mostrarAlerta(detalhes.error ?: String())
                } ?: atualizaLista()
            } ?: mostrarAlerta(getString(R.string.erro_padrao_api))
        }
    }

    private fun configuraFab() {
        binding.agendaPacienteFab.isVisible = false
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