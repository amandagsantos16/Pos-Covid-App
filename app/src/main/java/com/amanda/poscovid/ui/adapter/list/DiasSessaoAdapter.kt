package com.amanda.poscovid.ui.adapter.list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amanda.poscovid.R
import com.amanda.poscovid.modelo.Noticia
import com.amanda.poscovid.ui.adapter.viewholder.DiasSemanaViewHolder

class DiasSemanaAdapter(private val context: Context?) : RecyclerView.Adapter<DiasSemanaViewHolder>() {

    private val dias: MutableList<String> = mutableListOf()
    var clickListener: ((Int) -> Unit) = { }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiasSemanaViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_dia_semana, parent, false)

        return DiasSemanaViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dias.size
    }

    override fun onBindViewHolder(holder: DiasSemanaViewHolder, position: Int) {
        holder.bindData(dias[position])
        holder.itemView.setOnClickListener { clickListener(position) }
    }

    fun atualizaLista(lista: List<String>) {
        this.dias.clear()
        this.dias.addAll(lista)
        notifyDataSetChanged()
    }
}