package com.amanda.poscovid.ui.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.amanda.poscovid.databinding.FragmentHomeBinding
import com.amanda.poscovid.ui.fragment.BaseAppFragment

class HomeFragment : BaseAppFragment() {

    private lateinit var binding: FragmentHomeBinding

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
            navigateTo(HomeFragmentDirections.actionHomeToEspacoSaude())
        }
    }
}