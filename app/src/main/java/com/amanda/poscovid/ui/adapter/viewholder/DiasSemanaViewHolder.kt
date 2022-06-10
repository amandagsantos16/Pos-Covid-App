package com.amanda.poscovid.ui.adapter.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.amanda.poscovid.R

class DiasSemanaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindData(dia: String) {
        val textView = itemView.findViewById<TextView>(R.id.item_dia_semana_textview)
        textView.text = dia
    }
}