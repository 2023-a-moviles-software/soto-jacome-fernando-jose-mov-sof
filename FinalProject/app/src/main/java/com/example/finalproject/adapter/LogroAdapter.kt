package com.example.finalproject.adapter

import android.icu.text.AlphabeticIndex.Record
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.bumptech.glide.Glide
import com.example.finalproject.R
import com.example.finalproject.entity.Logro

class LogroAdapter(private var listLogro: List<Logro>) :
    RecyclerView.Adapter<LogroAdapter.LogroViewHolder>() {


    inner class LogroViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nombreLogro = view.findViewById<TextView>(R.id.tv_nombre_logro)
        val userNombreLogro = view.findViewById<TextView>(R.id.tv_user_nombre_logro)
        val userUrlImagenLogro = view.findViewById<ImageView>(R.id.iv_imagenLogro)
        val horaLogro = view.findViewById<TextView>(R.id.tv_hora_logro)
        fun render(logro: Logro) {
            Glide.with(userUrlImagenLogro.context).load(logro.userImagenLogro)
                .into(userUrlImagenLogro)
            nombreLogro.text = logro.nombreLogro
            userNombreLogro.text = logro.userNombreLogro
            horaLogro.text = logro.horaLogro
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LogroViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return LogroViewHolder(layoutInflater.inflate(R.layout.item_logro, parent, false))
    }

    override fun onBindViewHolder(holder: LogroViewHolder, position: Int) {
        val logroItem = listLogro[position]
        holder.render(logroItem)
    }

    override fun getItemCount(): Int {
        return listLogro.size
    }


}