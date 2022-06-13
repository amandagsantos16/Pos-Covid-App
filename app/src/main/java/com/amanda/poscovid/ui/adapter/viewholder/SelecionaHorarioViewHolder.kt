package com.amanda.poscovid.ui.adapter.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.amanda.poscovid.R
import com.amanda.poscovid.extension.FULL_API_FORMAT
import com.amanda.poscovid.extension.formatDate
import com.amanda.poscovid.extension.formatToUi
import com.amanda.poscovid.modelo.Horario
import java.text.SimpleDateFormat
import java.util.*

class SelecionaHorarioViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindData(horarios: Horario, clickListener: ((Horario) -> Unit)) {
        val text = itemView.findViewById<TextView>(R.id.item_seleciona_horario_textview)

        val simpleDateFormat = SimpleDateFormat(FULL_API_FORMAT)
        val parse = simpleDateFormat.parse(horarios.hora)
        val formatDate = parse.formatDate()
        text.text = formatDate
        itemView.setOnClickListener { clickListener(horarios) }
    }
}