package com.amanda.poscovid.ui.adapter.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.amanda.poscovid.R
import com.amanda.poscovid.modelo.Noticia

class EstudosViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindData(noticia : Noticia, clickListener: ((Noticia) -> Unit)) {
        val titulo = itemView.findViewById<TextView>(R.id.estudo_titulo)
        val subtitulo = itemView.findViewById<TextView>(R.id.estudo_subtitulo)
        titulo.text = noticia.titulo
        subtitulo.text = noticia.subtitulo
        itemView.setOnClickListener { clickListener(noticia) }
    }
}