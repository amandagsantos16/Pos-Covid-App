package com.amanda.poscovid.ui.adapter.list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amanda.poscovid.R
import com.amanda.poscovid.ui.adapter.viewholder.EditaHorarioViewHolder

class EditaHorarioAdapter(private val context: Context?) : RecyclerView.Adapter<EditaHorarioViewHolder>() {

    private val horarios: MutableList<String> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EditaHorarioViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_item_horario, parent, false)

        return EditaHorarioViewHolder(view)
    }

    override fun getItemCount(): Int {
        return horarios.size
    }

    override fun onBindViewHolder(holder: EditaHorarioViewHolder, position: Int) {
        holder.bind(horarios[position], position) {
            horarios.removeAt(position)
            atualizaAdapter()
        }
    }

    fun atualizaLista(lista: List<String>) {
        this.horarios.clear()
        this.horarios.addAll(lista)
        atualizaAdapter()
    }

    fun addItem(item: String) {
        this.horarios.add(item)
        atualizaAdapter()
    }

    private fun atualizaAdapter() {
        this.horarios.sort()
        notifyDataSetChanged()
    }
}