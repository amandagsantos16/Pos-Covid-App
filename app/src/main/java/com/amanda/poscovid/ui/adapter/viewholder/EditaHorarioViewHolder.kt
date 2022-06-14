package com.amanda.poscovid.ui.adapter.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.amanda.poscovid.R
import com.amanda.poscovid.extension.FULL_API_FORMAT
import com.amanda.poscovid.extension.formatDate
import com.amanda.poscovid.modelo.Horario
import java.text.SimpleDateFormat

class EditaHorarioViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(item: Horario, deleteApiListener: ((Horario) -> Unit)) {
        val textView = itemView.findViewById<TextView>(R.id.item_horario_textView)
        val imageView = itemView.findViewById<ImageView>(R.id.item_horario_delete)


        val simpleDateFormat = SimpleDateFormat(FULL_API_FORMAT)
        textView.text = simpleDateFormat.parse(item.hora).formatDate()
        imageView.setOnClickListener {
            deleteApiListener(item)
        }
    }
}