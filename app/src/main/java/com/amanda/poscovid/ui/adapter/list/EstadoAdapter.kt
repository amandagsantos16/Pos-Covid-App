package com.amanda.poscovid.ui.adapter.list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amanda.poscovid.R
import com.amanda.poscovid.modelo.Estado
import com.amanda.poscovid.ui.adapter.viewholder.EstadoViewHolder
import java.util.*

class EstadoAdapter(private val context: Context?) : RecyclerView.Adapter<EstadoViewHolder>() {

    private var arraylist: List<Estado> = listOf()
    private val estados: MutableList<Estado> = mutableListOf()

    var clickListener: ((Estado) -> Unit) = { }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EstadoViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_estados, parent, false)

        return EstadoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return estados.size
    }

    override fun onBindViewHolder(holder: EstadoViewHolder, position: Int) {
        holder.bind(estados[position], clickListener)
    }

    fun atualizaLista(assinantes: List<Estado>) {
        this.estados.clear()
        this.estados.addAll(assinantes)
        this.arraylist = assinantes
        notifyDataSetChanged()
    }

    fun filter(filtrar: String) {
        val charText = filtrar.toLowerCase(Locale.getDefault())
        estados.clear()
        if (charText.isEmpty()) {
            estados.addAll(arraylist)
        } else {
            for (wp in arraylist) {
                when {
                    wp.uf!!.toLowerCase(Locale.getDefault()).contains(charText) -> estados.add(wp)
                    wp.state!!.toLowerCase(Locale.getDefault()).contains(charText) -> estados.add(wp)
                }
            }
        }
        notifyDataSetChanged()
    }
}