package com.amanda.poscovid.ui.adapter.list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amanda.poscovid.R
import com.amanda.poscovid.modelo.Noticia
import com.amanda.poscovid.ui.adapter.viewholder.NoticiasViewHolder

class NoticiasAdapter(private val context: Context?) : RecyclerView.Adapter<NoticiasViewHolder>() {

    private val noticias: MutableList<Noticia> = mutableListOf()
    var clickListener: ((Noticia) -> Unit) = { }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticiasViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_noticia, parent, false)
        return NoticiasViewHolder(view)
    }

    override fun getItemCount(): Int {
        return noticias.size
    }

    override fun onBindViewHolder(holder: NoticiasViewHolder, position: Int) {
        holder.bind(noticias[position], clickListener)
    }

    fun atualizaLista(noticias: List<Noticia>) {
        this.noticias.clear()
        this.noticias.addAll(noticias)
        notifyDataSetChanged()
    }
}