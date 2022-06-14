package com.amanda.poscovid.ui.adapter.viewholder

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.amanda.poscovid.R
import com.amanda.poscovid.extension.FULL_API_FORMAT
import com.amanda.poscovid.extension.formatDate
import com.amanda.poscovid.extension.formatToUi
import com.amanda.poscovid.modelo.Agendamento
import com.amanda.poscovid.modelo.Status
import java.text.SimpleDateFormat

class ConsultaViewHolder(itemView: View, private val isPsicolog: Boolean) : RecyclerView.ViewHolder(itemView) {

    lateinit var titulo: TextView
    lateinit var data: TextView
    lateinit var horario: TextView
    lateinit var editar: TextView
    lateinit var deletar: TextView
    lateinit var status: TextView
    lateinit var confirmar: TextView
    lateinit var agendamento: Agendamento
    private val context: Context
        get() = itemView.context

    fun bind(
        agendamento: Agendamento,
        editListener: ((Agendamento) -> Unit),
        deleteListener: ((Agendamento) -> Unit),
        confirmarListener: ((Agendamento) -> Unit)
    ) {
        titulo = itemView.findViewById(R.id.agendamento_titulo)
        data = itemView.findViewById(R.id.agendamento_data)
        horario = itemView.findViewById(R.id.agendamento_horario)
        editar = itemView.findViewById(R.id.agendamento_editar)
        deletar = itemView.findViewById(R.id.agendamento_excluir)
        status = itemView.findViewById(R.id.agendamento_status)
        confirmar = itemView.findViewById(R.id.agendamento_confirmar)
        this.agendamento = agendamento

        setTitulo()
        setData()
        setStatus()
        editar.setOnClickListener { editListener(agendamento) }
        deletar.setOnClickListener { deleteListener(agendamento) }
        confirmar.setOnClickListener { confirmarListener(agendamento) }
    }

    private fun setStatus() {
        when (agendamento.statusAgendamento) {
            Status.PENDENTE_PSICOLOGO -> {
                status.text = "Pendente"
                status.background = ContextCompat.getDrawable(context, R.drawable.background_pendente)
                confirmar.visibility = if(isPsicolog) View.VISIBLE else View.GONE
            }
            Status.PENDENTE_PACIENTE -> {
                status.text = "Pendente"
                status.background = ContextCompat.getDrawable(context, R.drawable.background_pendente)
                confirmar.visibility = if(isPsicolog) View.GONE else View.VISIBLE
            }
            Status.CONFIRMADO -> {
                status.text = "Confirmado"
                status.background = ContextCompat.getDrawable(context, R.drawable.background_confirmado)
                confirmar.visibility = View.GONE
            }
        }
    }

    private fun setData() {
        val simpleDateFormat = SimpleDateFormat(FULL_API_FORMAT)
        horario.text = simpleDateFormat.parse(agendamento.horario?.hora).formatDate()
        data.text = simpleDateFormat.parse(agendamento.data).formatToUi()
    }

    private fun setTitulo() {
        titulo.text =
            itemView.context.getString(R.string.titulo_agendamento, if (isPsicolog) agendamento.paciente?.nome else agendamento.psicologo?.nome)
    }

}