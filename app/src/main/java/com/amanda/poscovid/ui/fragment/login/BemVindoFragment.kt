package com.amanda.poscovid.ui.fragment.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.amanda.poscovid.databinding.FragmentBemVindoBinding
import com.amanda.poscovid.ui.fragment.BaseAppFragment

class BemVindoFragment : BaseAppFragment() {

    private lateinit var binding: FragmentBemVindoBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentBemVindoBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setListeners()
    }

    private fun setListeners() {
        binding.bemVindoCadastreSe.setOnClickListener {
            navigateTo(BemVindoFragmentDirections.bemVindoParaCadastrarLogin())
        }
        binding.bemVindoEntrar.setOnClickListener {
            navigateTo(BemVindoFragmentDirections.bemVindoParaLogin())
        }
    }
}