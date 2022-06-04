package com.amanda.poscovid.ui.fragment

import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.navArgs
import com.amanda.poscovid.databinding.FragmentEstudosDetalhesBinding

class EstudosDetalhesFragment : BaseAppFragment() {

    private val args by navArgs<EstudosDetalhesFragmentArgs>()
    private val estudo by lazy { args.estudo }
    private lateinit var binding: FragmentEstudosDetalhesBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        (activity as AppCompatActivity).supportActionBar?.show()
        binding = FragmentEstudosDetalhesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.estudoDetalhesTitulo.text = estudo.titulo
        binding.estudoDetalhesCorpo.text = estudo.corpo
        binding.estudoDetalhesSubtitulo.text = estudo.subtitulo
        binding.estudoDetalhesUrl.setOnClickListener {
            val intent = Intent(ACTION_VIEW)
            intent.data = Uri.parse(estudo.url)
            startActivity(
                intent
            )
        }
    }
}