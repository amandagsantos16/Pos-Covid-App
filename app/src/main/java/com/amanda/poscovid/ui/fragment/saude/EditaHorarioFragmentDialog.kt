package com.amanda.poscovid.ui.fragment.saude

import android.app.DatePickerDialog
import android.app.ProgressDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.amanda.poscovid.databinding.DialogEditaHorarioBinding
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
    }

    private fun configuraClicks() {
        binding.editaHorarioFechar.setOnClickListener {
            fechaDialog()
        }
        binding.editaHorarioSalvar.setOnClickListener {
            fechaDialog()
        }
        binding.editaHorarioAdicionar.setOnClickListener {
            val calendar = Calendar.getInstance()
            val hora = calendar.get(Calendar.HOUR_OF_DAY)
            val minuto = calendar.get(Calendar.MINUTE)
            TimePickerDialog(requireContext(), { _, h, m ->
                adapter.addItem("$h:$m")
            }, hora, minuto, true).show()
        }
    }

    private fun buscaHorariosCadastrados() {
        viewModel.getHorariosCadastrados(dia).observe(viewLifecycleOwner) {

        }
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
}