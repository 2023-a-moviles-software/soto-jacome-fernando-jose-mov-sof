package com.example.finalproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.finalproject.R
import com.example.finalproject.entity.Actividad

class ActividadAdapter(private var listActividad: List<Actividad>) :
    RecyclerView.Adapter<ActividadAdapter.ActividadViewHolder>() {
    inner class ActividadViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val urlImgActividad = view.findViewById<ImageView>(R.id.iv_imagenActividad)
        val nombreActividad = view.findViewById<TextView>(R.id.tv_nombre_actividad)
        val dificultad = view.findViewById<TextView>(R.id.tv_dificultad)
        val calificacion = view.findViewById<TextView>(R.id.tv_calificacion)
        val calorias = view.findViewById<TextView>(R.id.tv_calorias)

        fun render(actividad: Actividad) {
            Glide.with(urlImgActividad.context).load(actividad.imagenActividad).into(urlImgActividad)
            nombreActividad.text = actividad.nombreActividad
            dificultad.text = actividad.dificultad
            calificacion.text = actividad.dificultad
            calorias.text = actividad.calorias.toString()
            calificacion.text = actividad.calificacion.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActividadViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ActividadViewHolder(layoutInflater.inflate(R.layout.item_actividad, parent, false))
    }


    override fun onBindViewHolder(holder: ActividadViewHolder, position: Int) {
        val actividadItem = listActividad[position]
        holder.render(actividadItem)
    }

    override fun getItemCount(): Int {
        return listActividad.size
    }

}