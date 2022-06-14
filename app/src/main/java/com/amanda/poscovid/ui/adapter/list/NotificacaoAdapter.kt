package com.amanda.poscovid.ui.adapter.list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.amanda.poscovid.R
import com.amanda.poscovid.modelo.Notificacao

class NotificacaoAdapter(private val context: Context?) : RecyclerView.Adapter<NotificacaoAdapter.NotificacaoViewHolder>() {

    private val notificacoes: MutableList<Notificacao> = mutableListOf()
    var clickListener: ((Notificacao) -> Unit) = { }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificacaoViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_notificacao, parent, false)

        return NotificacaoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return notificacoes.size
    }

    override fun onBindViewHolder(holder: NotificacaoViewHolder, position: Int) {
        holder.bind(notificacoes[position], clickListener)
    }

    fun atualizaLista(lista: List<Notificacao>) {
        this.notificacoes.clear()
        this.notificacoes.addAll(lista)
        notifyDataSetChanged()
    }

    class NotificacaoViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(notificacao: Notificacao, clickListener: ((Notificacao) -> Unit) = { }) {
            val textView = itemView.findViewById<TextView>(R.id.notificacao_texto)
            textView.text = notificacao.mensagem
            itemView.setOnClickListener { clickListener(notificacao) }
        }
    }
}