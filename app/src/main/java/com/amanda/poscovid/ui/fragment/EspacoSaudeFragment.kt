package com.amanda.poscovid.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.amanda.poscovid.databinding.FragmentEspacoSaudeBinding

class EspacoSaudeFragment : BaseAppFragment() {

    private lateinit var binding: FragmentEspacoSaudeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        (activity as AppCompatActivity).supportActionBar?.show()
        binding = FragmentEspacoSaudeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setOnClicks()
    }

    private fun setOnClicks() {
        binding.serPsicologo.setOnClickListener {
            navigateTo(
                EspacoSaudeFragmentDirections.actionEspacoSaudeToCadastrarPsicologo()
            )
        }
    }
}