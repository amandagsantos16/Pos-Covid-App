package com.amanda.poscovid.ui.adapter.list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amanda.poscovid.R
import com.amanda.poscovid.modelo.Noticia
import com.amanda.poscovid.ui.adapter.viewholder.EstudosViewHolder

class EstudosAdapter(private val context: Context?) : RecyclerView.Adapter<EstudosViewHolder>() {

    private val noticias: MutableList<Noticia> = mutableListOf()
    var clickListener: ((Noticia) -> Unit) = { }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EstudosViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_estudo, parent, false)

        return EstudosViewHolder(view)
    }

    override fun getItemCount(): Int {
        return noticias.size
    }

    override fun onBindViewHolder(holder: EstudosViewHolder, position: Int) {
        holder.bindData(noticias[position], clickListener)
    }

    fun atualizaLista(noticias: List<Noticia>) {
        this.noticias.clear()
        this.noticias.addAll(noticias)
        notifyDataSetChanged()
    }
}