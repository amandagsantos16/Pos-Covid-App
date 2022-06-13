package com.amanda.poscovid.ui.fragment.saude

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.amanda.poscovid.R
import com.amanda.poscovid.databinding.FragmentHorariosCadastradosBinding
import com.amanda.poscovid.ui.adapter.list.DiasSemanaAdapter
import com.amanda.poscovid.ui.fragment.BaseAppFragment

class HorariosCadastadosFragment : BaseAppFragment() {

    private lateinit var binding: FragmentHorariosCadastradosBinding
    private val adapter by lazy { DiasSemanaAdapter(context) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        (activity as AppCompatActivity).supportActionBar?.show()
        binding = FragmentHorariosCadastradosBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configuraAdapter()
    }

    private fun configuraAdapter() {
        adapter.atualizaLista(resources.getStringArray(R.array.dias).asList())
        binding.horariosCadastradosRecyclerView.adapter = adapter
        adapter.clickListener = {
            editaHorario(it)
        }
    }

    private fun editaHorario(dia: Int) {
        navigateTo(HorariosCadastadosFragmentDirections.actionHorarioCadastradosToEditaHorario(dia))
    }
}