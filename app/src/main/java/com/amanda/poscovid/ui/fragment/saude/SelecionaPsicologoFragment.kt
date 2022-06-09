package com.amanda.poscovid.ui.fragment.saude

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.amanda.poscovid.databinding.FragmentSelecionaPsicologoBinding
import com.amanda.poscovid.ui.adapter.list.PsicologoAdapter
import com.amanda.poscovid.ui.fragment.BaseAppFragment

class SelecionaPsicologoFragment : BaseAppFragment() {

    private lateinit var binding: FragmentSelecionaPsicologoBinding
    private val adapter by lazy { PsicologoAdapter(context) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        (activity as AppCompatActivity).supportActionBar?.show()
        binding = FragmentSelecionaPsicologoBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.selecionaPsicologoRecyclerView.adapter = adapter
        adapter.clickListener = {
            navigateTo(
                SelecionaPsicologoFragmentDirections.actionSelecionaPsicologoToSelecionaHorario()
            )
        }
    }
}