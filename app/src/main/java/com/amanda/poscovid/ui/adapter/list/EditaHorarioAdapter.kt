package com.amanda.poscovid.ui.adapter.list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amanda.poscovid.R
import com.amanda.poscovid.modelo.Horario
import com.amanda.poscovid.ui.adapter.viewholder.EditaHorarioViewHolder

class EditaHorarioAdapter(private val context: Context?) : RecyclerView.Adapter<EditaHorarioViewHolder>() {

    val horarios: MutableList<Horario> = mutableListOf()
    var deleteListener: ((Horario) -> Unit) = { }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EditaHorarioViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_item_horario, parent, false)

        return EditaHorarioViewHolder(view)
    }

    override fun getItemCount(): Int {
        return horarios.size
    }

    override fun onBindViewHolder(holder: EditaHorarioViewHolder, position: Int) {
        holder.bind(horarios[position], deleteListener)
    }

    fun atualizaLista(lista: List<Horario>) {
        this.horarios.clear()
        this.horarios.addAll(lista)
        atualizaAdapter()
    }

    private fun atualizaAdapter() {
        notifyDataSetChanged()
    }
}