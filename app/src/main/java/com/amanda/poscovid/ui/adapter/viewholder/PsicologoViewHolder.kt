package com.amanda.poscovid.ui.adapter.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.amanda.poscovid.R
import com.amanda.poscovid.modelo.Psicologo

class PsicologoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindData(psicologo: Psicologo, clickListener: (() -> Unit) = { }) {
        val titulo = itemView.findViewById<TextView>(R.id.item_psicologo_titulo)
        val descricao = itemView.findViewById<TextView>(R.id.item_psicologo_descricao)
        val especialidade = itemView.findViewById<TextView>(R.id.item_psicologo_especialidade)

        titulo.text = psicologo.nome
        descricao.text = psicologo.resumo
        especialidade.text = psicologo.especializacoes
    }
}