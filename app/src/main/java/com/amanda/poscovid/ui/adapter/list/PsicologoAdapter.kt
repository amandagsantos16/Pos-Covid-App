package com.amanda.poscovid.ui.adapter.list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amanda.poscovid.R
import com.amanda.poscovid.ui.adapter.viewholder.PsicologoViewHolder

class PsicologoAdapter(private val context: Context?) : RecyclerView.Adapter<PsicologoViewHolder>() {

    private val psicologos: MutableList<Any> = mutableListOf()

    var clickListener: ((Any) -> Unit) = { }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PsicologoViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_psicologo, parent, false)

        return PsicologoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 5
    }

    override fun onBindViewHolder(holder: PsicologoViewHolder, position: Int) {
    }

    fun atualizaLista(consultas: List<Any>) {
        this.psicologos.clear()
        this.psicologos.addAll(consultas)
        notifyDataSetChanged()
    }
}