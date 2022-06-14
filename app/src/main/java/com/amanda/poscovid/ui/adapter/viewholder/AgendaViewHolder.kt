package com.amanda.poscovid.ui.adapter.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.amanda.poscovid.R
import com.amanda.poscovid.extension.FULL_API_FORMAT
import com.amanda.poscovid.extension.formatDate
import com.amanda.poscovid.extension.formatToUi
import com.amanda.poscovid.modelo.Agendamento
import java.text.SimpleDateFormat

class ConsultaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(agendamento: Agendamento, editListener: ((Agendamento) -> Unit), deleteListener: ((Agendamento) -> Unit)) {
        val titulo = itemView.findViewById<TextView>(R.id.agendamento_titulo)
        val data = itemView.findViewById<TextView>(R.id.agendamento_data)
        val horario = itemView.findViewById<TextView>(R.id.agendamento_horario)
        val editar = itemView.findViewById<TextView>(R.id.agendamento_editar)
        val deletar = itemView.findViewById<TextView>(R.id.agendamento_excluir)

        val simpleDateFormat = SimpleDateFormat(FULL_API_FORMAT)

        titulo.text = itemView.context.getString(R.string.titulo_agendamento, agendamento.psicologo?.nome)
        horario.text = simpleDateFormat.parse(agendamento.horario?.hora).formatDate()
        data.text = simpleDateFormat.parse(agendamento.data).formatToUi()
        editar.setOnClickListener { editListener(agendamento) }
        deletar.setOnClickListener { deleteListener(agendamento) }
    }

}