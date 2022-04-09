package com.amanda.poscovid.ui.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.amanda.poscovid.databinding.FragmentHomeBinding
import com.amanda.poscovid.ui.fragment.BaseAppFragment

class HomeFragment : BaseAppFragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        (activity as AppCompatActivity).supportActionBar?.show()
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.saude.setOnClickListener {

        }
        binding.saude.setOnClickListener {

        }
        binding.saude.setOnClickListener {
            navigateTo(HomeFragmentDirections.actionHomeToEspacoSaude())
        }
    }
}