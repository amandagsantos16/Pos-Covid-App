package com.amanda.poscovid.ui.fragment.saude

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.amanda.poscovid.R
import com.amanda.poscovid.databinding.FragmentSelecionaPsicologoBinding
import com.amanda.poscovid.modelo.Psicologo
import com.amanda.poscovid.ui.adapter.list.PsicologoAdapter
import com.amanda.poscovid.ui.fragment.BaseAppFragment
import com.amanda.poscovid.ui.viewModel.SelecionaPsicologoViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SelecionaPsicologoFragment : BaseAppFragment() {

    private lateinit var binding: FragmentSelecionaPsicologoBinding
    private val adapter by lazy { PsicologoAdapter(context) }
    private val progressDialog by lazy { ProgressDialog(context, ProgressDialog.STYLE_HORIZONTAL) }
    private val viewModel by viewModel<SelecionaPsicologoViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        (activity as AppCompatActivity).supportActionBar?.show()
        binding = FragmentSelecionaPsicologoBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configuraProgress()
        configuraAdapter()
        atualizaLista()
    }

    private fun atualizaLista() {
        viewModel.getPsicologos().observe(viewLifecycleOwner) {
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

    private fun atualizaAdapter(lista: List<Psicologo>) {
        adapter.atualizaLista(lista)
    }

    private fun configuraAdapter() {
        binding.selecionaPsicologoRecyclerView.adapter = adapter
        adapter.clickListener = {
            navigateTo(
                SelecionaPsicologoFragmentDirections.actionSelecionaPsicologoToSelecionaHorario(it)
            )
        }
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