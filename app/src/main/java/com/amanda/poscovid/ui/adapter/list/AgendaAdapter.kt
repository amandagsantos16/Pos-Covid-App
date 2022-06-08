package com.amanda.poscovid.ui.adapter.list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amanda.poscovid.R
import com.amanda.poscovid.modelo.Consulta
import com.amanda.poscovid.ui.adapter.viewholder.ConsultaViewHolder

class AgendaAdapter(private val context: Context?) : RecyclerView.Adapter<ConsultaViewHolder>() {

    private val consulta: MutableList<Consulta> = mutableListOf()

    var clickListener: ((Consulta) -> Unit) = { }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConsultaViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_agendamento, parent, false)

        return ConsultaViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 5
    }

    override fun onBindViewHolder(holder: ConsultaViewHolder, position: Int) {
//        holder.bind(consulta[position], clickListener)
    }

    fun atualizaLista(consultas: List<Consulta>) {
        this.consulta.clear()
        this.consulta.addAll(consultas)
        notifyDataSetChanged()
    }
}