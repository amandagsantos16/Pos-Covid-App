package com.amanda.poscovid.ui.fragment.saude

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.amanda.poscovid.databinding.FragmentAgendaPacienteBinding
import com.amanda.poscovid.ui.fragment.BaseAppFragment

class AgendaPacienteFragment: BaseAppFragment() {

    private lateinit var binding: FragmentAgendaPacienteBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        (activity as AppCompatActivity).supportActionBar?.show()
        binding = FragmentAgendaPacienteBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}