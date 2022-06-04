package com.amanda.poscovid.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.amanda.poscovid.R
import com.amanda.poscovid.databinding.FragmentCovidNoMundoBinding
import com.amanda.poscovid.databinding.ProcurarEstadosBinding
import com.amanda.poscovid.modelo.Estado
import com.amanda.poscovid.ui.adapter.list.EstadoAdapter
import com.amanda.poscovid.ui.viewModel.CovidViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.NumberFormat

class CovidNoMundoFragment : BaseAppFragment() {

    private lateinit var binding: FragmentCovidNoMundoBinding

    private val viewModel: CovidViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        (activity as AppCompatActivity).supportActionBar?.show()
        binding = FragmentCovidNoMundoBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.buscaListaDeEstados().observe(viewLifecycleOwner) { resposta ->
            resposta?.apply {
                dados?.let {
                    it.data?.apply {
                        selecionaEstado(get(0))
                        configuraEstados(this)
                    }
                }
                detalhes?.let {
                    mostrarAlerta(it.error ?: String())
                }
            } ?: mostrarAlerta(getString(R.string.erro_padrao_api_get)) {
                findNavController().popBackStack()
            }
        }
    }

    private fun configuraEstados(data: List<Estado>?) {
        val estadoAdapter = EstadoAdapter(context)
        data?.let { estadoAdapter.atualizaLista(it) }
        binding.estadoSelecionado.setOnClickListener {
            val view = ProcurarEstadosBinding.inflate(layoutInflater, null, false)
            view.procurarEstadosLista.adapter = estadoAdapter
            view.procurarEstadosProcurar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(p0: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(p0: String?): Boolean {
                    p0?.let { estadoAdapter.filter(it) }
                    return false
                }

            })
            val alertDialog = AlertDialog.Builder(context!!)
                .setView(view.root)
                .show()

            estadoAdapter.clickListener = { estado ->
                alertDialog.dismiss()
                selecionaEstado(estado)
            }
        }
    }

    private fun selecionaEstado(estado: Estado) {
        binding.estadoSelecionado.setText(estado.state)
        binding.casosConfirmados.text = NumberFormat.getInstance().format(estado.cases)
        binding.mortes.text = NumberFormat.getInstance().format(estado.deaths)
    }
}