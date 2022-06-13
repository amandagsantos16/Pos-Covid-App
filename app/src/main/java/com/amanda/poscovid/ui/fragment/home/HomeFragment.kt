package com.amanda.poscovid.ui.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.amanda.poscovid.databinding.FragmentHomeBinding
import com.amanda.poscovid.preferences.IUserPreferenceHelper
import com.amanda.poscovid.ui.fragment.BaseAppFragment
import org.koin.android.ext.android.inject

class HomeFragment : BaseAppFragment() {

    private lateinit var binding: FragmentHomeBinding
    private val userPreferenceManager: IUserPreferenceHelper by inject()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.covidNoMundo.setOnClickListener {
            navigateTo(HomeFragmentDirections.actionHomeToCovidNoMundo())
        }
        binding.estudos.setOnClickListener {
            navigateTo(HomeFragmentDirections.actionHomeToUltimosEstudos())
        }
        binding.saude.setOnClickListener {
            if (userPreferenceManager.id.isNotEmpty()) {
                navigateTo(HomeFragmentDirections.actionHomeToEspacoSaude())
            } else {
                mostrarAlerta("Você precisa fazer login para poder continuar")
            }
        }
    }
}