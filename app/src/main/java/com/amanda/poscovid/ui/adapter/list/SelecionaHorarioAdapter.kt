package com.amanda.poscovid.ui.adapter.list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amanda.poscovid.R
import com.amanda.poscovid.ui.adapter.viewholder.SelecionaHorarioViewHolder

class SelecionaHorarioAdapter(private val context: Context?) : RecyclerView.Adapter<SelecionaHorarioViewHolder>() {

    private val horarios: MutableList<Any> = mutableListOf()

    var clickListener: (() -> Unit) = { }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelecionaHorarioViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_seleciona_horario, parent, false)

        return SelecionaHorarioViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 5
    }

    override fun onBindViewHolder(holder: SelecionaHorarioViewHolder, position: Int) {
        holder.bindData(clickListener)
    }

    fun atualizaLista(consultas: List<Any>) {
        this.horarios.clear()
        this.horarios.addAll(consultas)
        notifyDataSetChanged()
    }
}