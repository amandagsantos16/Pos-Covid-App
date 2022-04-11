package com.amanda.poscovid.ui.adapter.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.amanda.poscovid.R
import com.amanda.poscovid.modelo.Noticia

class NoticiasViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(noticia: Noticia, clickListener: ((Noticia) -> Unit)) {
        val textView = itemView.findViewById<TextView>(R.id.noticia_titulo)
        textView.text = noticia.titulo
        val imageView = itemView.findViewById<ImageView>(R.id.noticia_imageView)
        imageView.setImageDrawable(ContextCompat.getDrawable(itemView.context, noticia.imagem))
        itemView.setOnClickListener { clickListener(noticia) }
    }

}