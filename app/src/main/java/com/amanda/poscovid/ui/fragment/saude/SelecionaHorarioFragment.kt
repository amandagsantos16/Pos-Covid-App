package com.amanda.poscovid.ui.fragment.saude

import android.icu.text.DateFormat.DAY
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.amanda.poscovid.databinding.FragmentSelecionaHorarioBinding
import com.amanda.poscovid.extension.showDataPickerDialog
import com.amanda.poscovid.ui.adapter.list.SelecionaHorarioAdapter
import com.amanda.poscovid.ui.fragment.BaseAppFragment
import java.text.SimpleDateFormat
import java.util.*

class SelecionaHorarioFragment : BaseAppFragment() {

    private lateinit var binding: FragmentSelecionaHorarioBinding
    private val adapter by lazy { SelecionaHorarioAdapter(context) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        (activity as AppCompatActivity).supportActionBar?.show()
        binding = FragmentSelecionaHorarioBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.selecionaHorarioRecyclerView.adapter = adapter
        configuraEditText()
    }

    private fun configuraEditText() {
        val calendar = Calendar.getInstance()
        setEditText(calendar)
        binding.itemSelecionaHorarioEditText.setOnClickListener {
            showDataPickerDialog(it.context, calendar) { _, year, month, day ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, month)
                calendar.set(Calendar.DAY_OF_MONTH, day)
                setEditText(calendar)
            }
        }
    }

    private fun setEditText(calendar: Calendar) {
        binding.itemSelecionaHorarioEditText.setText(SimpleDateFormat("dd/MM/yyyy").format(calendar.time))
    }
}