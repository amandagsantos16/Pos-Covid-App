package com.amanda.poscovid.ui.fragment.saude

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.amanda.poscovid.databinding.FragmentAreaPsicologoBinding
import com.amanda.poscovid.ui.fragment.BaseAppFragment

class AreaPsicologoFragment : BaseAppFragment() {

    private lateinit var binding: FragmentAreaPsicologoBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        (activity as AppCompatActivity).supportActionBar?.show()
        binding = FragmentAreaPsicologoBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configuraClick()
    }


    private fun configuraClick() {
        binding.horariosCadastrados.setOnClickListener {
            navigateTo(
                AreaPsicologoFragmentDirections.actionAreaPsicologoToHorarioCadastrados()
            )
        }
        binding.agenda.setOnClickListener {
            navigateTo(
                AreaPsicologoFragmentDirections.actionAreaPsicologoToAgendaPsicologoFragment()
            )
        }
    }
}