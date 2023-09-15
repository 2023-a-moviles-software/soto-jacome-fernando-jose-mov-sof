package com.example.finalproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.R
import com.example.finalproject.entity.Recordatorio

class RecordatorioAdapter(private var listRecordatorio: List<Recordatorio>) :
    RecyclerView.Adapter<RecordatorioAdapter.RecordatorioViewHolder>() {

    inner class RecordatorioViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nombreRecordatorio = view.findViewById<TextView>(R.id.tv_nombre_recordatorio)
        val descripcionRecordatorio = view.findViewById<TextView>(R.id.tv_descripcion_recordatorio)
        val fechaRecordatorio = view.findViewById<TextView>(R.id.tv_fecha_recordatorio)
        val horaRecordatorio = view.findViewById<TextView>(R.id.tv_hora_recordatorio)

        fun render(recordatorio: Recordatorio) {
            nombreRecordatorio.text = recordatorio.nombreRecordario
            descripcionRecordatorio.text = recordatorio.descripcionRecordatorio
            fechaRecordatorio.text = recordatorio.fechaRecordatorio
            horaRecordatorio.text = recordatorio.horaRecordatorio
            
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordatorioViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return RecordatorioViewHolder(
            layoutInflater.inflate(
                R.layout.item_recordatorio,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return listRecordatorio.size
    }

    override fun onBindViewHolder(holder: RecordatorioViewHolder, position: Int) {
        val recordatorioItem = listRecordatorio[position]
        holder.render(recordatorioItem)
    }

}