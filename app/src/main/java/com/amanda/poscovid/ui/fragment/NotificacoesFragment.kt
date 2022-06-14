package com.amanda.poscovid.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.amanda.poscovid.databinding.FragmentNotificacoesBinding
import com.amanda.poscovid.preferences.IUserPreferenceHelper
import com.amanda.poscovid.ui.adapter.list.NotificacaoAdapter
import com.amanda.poscovid.ui.viewModel.HomeViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class NotificacoesFragment : BaseAppFragment() {

    private lateinit var binding: FragmentNotificacoesBinding

    private val viewModel: HomeViewModel by sharedViewModel()

    private val adapter by lazy { NotificacaoAdapter(context) }

    private val userPreference by inject<IUserPreferenceHelper>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentNotificacoesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter.clickListener = {
            if (it.pacienteId == userPreference.pacienteId) {
                navigateTo(
                    NotificacoesFragmentDirections.actionNotificacoesToAgendaPaciente()
                )
            }
            if (it.psicologoId == userPreference.psicologoId) {

            }
        }
        binding.recyclerView.adapter = adapter
        viewModel.notificacoes.observe(viewLifecycleOwner) {
            it?.let {
                adapter.atualizaLista(it)
            }
        }
    }
}