package com.amanda.poscovid.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.amanda.poscovid.R
import com.amanda.poscovid.databinding.FragmentCovidNoMundoBinding
import com.amanda.poscovid.ui.viewModel.CovidViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

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

                }
                detalhes?.let {
                    mostrarAlerta(it.error ?: String())
                }
            } ?: mostrarAlerta(getString(R.string.erro_padrao_api_get)) {
                findNavController().popBackStack()
            }
        }
    }
}