package com.amanda.poscovid.ui.adapter.list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amanda.poscovid.R
import com.amanda.poscovid.ui.adapter.viewholder.EstudosViewHolder

class EstudosAdapter(private val context: Context?) : RecyclerView.Adapter<EstudosViewHolder>() {

    var clickListener: (() -> Unit) = { }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EstudosViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_estudo, parent, false)

        return EstudosViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: EstudosViewHolder, position: Int) {

    }

    fun atualizaLista() {
    }
}