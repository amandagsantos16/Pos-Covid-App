package com.amanda.poscovid.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.amanda.poscovid.databinding.FragmentUltimosEstudosBinding
import com.amanda.poscovid.ui.adapter.list.EstudosAdapter

class UltimosEstudosFragment : BaseAppFragment() {

    private lateinit var binding: FragmentUltimosEstudosBinding

    private val adapter by lazy { EstudosAdapter(context) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentUltimosEstudosBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.adapter = adapter
    }
}