package com.amanda.poscovid.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.amanda.poscovid.databinding.FragmentUltimosEstudosBinding
import com.amanda.poscovid.ui.adapter.list.EstudosAdapter
import com.amanda.poscovid.ui.viewModel.CovidViewModel
import com.amanda.poscovid.ui.viewModel.NoticiaViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class UltimosEstudosFragment : BaseAppFragment() {

    private lateinit var binding: FragmentUltimosEstudosBinding

    private val viewModel: NoticiaViewModel by viewModel()

    private val adapter by lazy { EstudosAdapter(context) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentUltimosEstudosBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter.clickListener = {
            navigateTo(
                UltimosEstudosFragmentDirections.actionUltimosEstudosToDetalhes(it)
            )
        }
        binding.recyclerView.adapter = adapter
        viewModel.getAssinantes().observe(viewLifecycleOwner) {
            it?.let {
                adapter.atualizaLista(it)
            }
        }
    }
}