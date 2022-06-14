package com.amanda.poscovid.ui.fragment.saude

import android.app.ProgressDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.amanda.poscovid.R
import com.amanda.poscovid.databinding.DialogEditaHorarioBinding
import com.amanda.poscovid.modelo.Horario
import com.amanda.poscovid.ui.adapter.list.EditaHorarioAdapter
import com.amanda.poscovid.ui.viewModel.HorarioViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class EditaHorarioFragmentDialog : DialogFragment() {

    private val args by navArgs<EditaHorarioFragmentDialogArgs>()
    private val dia by lazy { args.dia }
    private lateinit var binding: DialogEditaHorarioBinding
    private val progressDialog by lazy { ProgressDialog(context, ProgressDialog.STYLE_HORIZONTAL) }
    private val controlador by lazy { findNavController() }
    private val adapter by lazy { EditaHorarioAdapter(context) }
    private val viewModel by viewModel<HorarioViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DialogEditaHorarioBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configuraProgress()
        buscaHorariosCadastrados()
        configuraClicks()
        configuraAdapter()
    }

    private fun configuraAdapter() {
        binding.editaHorarioRecyclerView.adapter = adapter
        adapter.deleteListener = {
            deletaHorario(it)
        }
    }

    private fun deletaHorario(horario: Horario) {
        viewModel.deletaHorario(horario).observe(viewLifecycleOwner) {
            it?.apply {
                detalhes?.let {
                    mostrarAlerta(detalhes.error ?: String())
                } ?: buscaHorariosCadastrados()
            } ?: mostrarAlerta(getString(R.string.erro_padrao_api))
        }
    }

    private fun configuraClicks() {
        binding.editaHorarioFechar.setOnClickListener {
            fechaDialog()
        }
        binding.editaHorarioAdicionar.setOnClickListener {
            val calendar = Calendar.getInstance()
            val hora = calendar.get(Calendar.HOUR_OF_DAY)
            val minuto = calendar.get(Calendar.MINUTE)
            TimePickerDialog(requireContext(), { _, h, m ->
                val stringHora = if (h < 10) "0$h" else "$h"
                val stringMinuto = if (m < 10) "0$m" else "$m"
                addHorario("$stringHora:$stringMinuto")
            }, hora, minuto, true).show()
        }
    }

    private fun addHorario(horario: String) {
        viewModel.salvaHorarios(dia, listOf(horario)).observe(viewLifecycleOwner) {
            it?.apply {
                dados?.let {
                    buscaHorariosCadastrados()
                }
                detalhes?.let {
                    mostrarAlerta(detalhes.error ?: String())
                }
            } ?: mostrarAlerta(getString(R.string.erro_padrao_api))
        }
    }

    private fun buscaHorariosCadastrados() {
        viewModel.getHorariosCadastrados(dia).observe(viewLifecycleOwner) {
            it?.apply {
                dados?.let {
                    atualizaAdapter(it)
                }
                detalhes?.let {
                    mostrarAlerta(detalhes.error ?: String())
                }
            } ?: mostrarAlerta(getString(R.string.erro_padrao_api))
        }
    }

    private fun atualizaAdapter(horarios: List<Horario>) {
        adapter.atualizaLista(horarios)
    }

    private fun fechaDialog() {
        controlador.popBackStack()
    }

    private fun configuraProgress() {
        viewModel.isLoading.observe(viewLifecycleOwner) { loading ->
            if (loading)
                progressDialog.show()
            else
                progressDialog.hide()
        }
    }

    fun mostrarAlerta(mensagem: String, listener: (() -> Unit)? = null) {
        context?.let {
            AlertDialog.Builder(it)
                .setCancelable(false)
                .setMessage(mensagem)
                .setPositiveButton("ok") { _, _ ->
                    listener?.invoke()
                }
                .create().show()
        }
    }
}