package com.amanda.poscovid.ui.adapter.viewholder

import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.amanda.poscovid.R
import com.amanda.poscovid.modelo.Estado

class EstadoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(estado: Estado, clickListener: ((Estado) -> Unit)) {
        val textView = itemView.findViewById<TextView>(R.id.estado_name)
        val layout = itemView.findViewById<LinearLayout>(R.id.estado_layout)
        textView.text = estado.state
        layout.setOnClickListener {
            clickListener.invoke(estado)
        }
    }

}