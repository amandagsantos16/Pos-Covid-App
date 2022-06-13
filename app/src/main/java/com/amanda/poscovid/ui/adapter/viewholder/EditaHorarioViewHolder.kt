package com.amanda.poscovid.ui.adapter.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.amanda.poscovid.R

class EditaHorarioViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(item: String, position: Int, deleteListener: ((Int) -> Unit)) {
        val textView = itemView.findViewById<TextView>(R.id.item_horario_textView)
        val imageView = itemView.findViewById<ImageView>(R.id.item_horario_delete)

        textView.text = item
        imageView.setOnClickListener {
            deleteListener.invoke(position)
        }
    }
}