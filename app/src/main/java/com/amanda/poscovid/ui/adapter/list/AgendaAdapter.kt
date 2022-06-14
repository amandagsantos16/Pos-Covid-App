package com.amanda.poscovid.ui.adapter.list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amanda.poscovid.R
import com.amanda.poscovid.modelo.Agendamento
import com.amanda.poscovid.modelo.Consulta
import com.amanda.poscovid.ui.adapter.viewholder.ConsultaViewHolder

class AgendaAdapter(private val context: Context?, private val isPsicolog: Boolean) : RecyclerView.Adapter<ConsultaViewHolder>() {

    private val consulta: MutableList<Agendamento> = mutableListOf()

    var editListener: ((Agendamento) -> Unit) = { }

    var deleteListener: ((Agendamento) -> Unit) = { }

    var confirmarListener: ((Agendamento) -> Unit) = { }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConsultaViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_agendamento, parent, false)

        return ConsultaViewHolder(view, isPsicolog)
    }

    override fun getItemCount(): Int {
        return consulta.size
    }

    override fun onBindViewHolder(holder: ConsultaViewHolder, position: Int) {
        holder.bind(consulta[position], editListener, deleteListener, confirmarListener)
    }

    fun atualizaLista(consultas: List<Agendamento>) {
        this.consulta.clear()
        this.consulta.addAll(consultas)
        notifyDataSetChanged()
    }
}