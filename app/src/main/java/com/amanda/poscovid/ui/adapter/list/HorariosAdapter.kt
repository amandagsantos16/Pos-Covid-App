package com.amanda.poscovid.ui.adapter.list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amanda.poscovid.R
import com.amanda.poscovid.ui.adapter.viewholder.HorarioViewHolder

class HorariosAdapter(private val context: Context?) : RecyclerView.Adapter<HorarioViewHolder>() {

    private val horarios: MutableList<String> = mutableListOf()
    var clickListener: ((String) -> Unit) = { }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorarioViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_horario, parent, false)

        return HorarioViewHolder(view)
    }

    override fun getItemCount(): Int {
        return horarios.size
    }

    override fun onBindViewHolder(holder: HorarioViewHolder, position: Int) {
    }

    fun atualizaLista(lista: List<String>) {
        this.horarios.clear()
        this.horarios.addAll(lista)
        notifyDataSetChanged()
    }
}