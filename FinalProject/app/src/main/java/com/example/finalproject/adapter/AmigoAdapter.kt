package com.example.finalproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.finalproject.R
import com.example.finalproject.entity.Amigo



class AmigoAdapter(private var listAmigos: List<Amigo>) :
    RecyclerView.Adapter<AmigoAdapter.AmigoViewHolder>() {


    inner class AmigoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val urlImgAmigo  = view.findViewById<ImageView>(R.id.iv_amigo)
        val nombreAmigo = view.findViewById<TextView>(R.id.tv_nombre_amigo)
        val experienciaAmigo = view.findViewById<TextView>(R.id.tv_experiencia_amigo)

        fun render(amigo: Amigo) {
            Glide.with(urlImgAmigo.context).load(amigo.urlImgAmigo)
                .into(urlImgAmigo)
            nombreAmigo.text = amigo.nombreAmigo
            experienciaAmigo.text = amigo.experienciaAmigo

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AmigoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return AmigoViewHolder(layoutInflater.inflate(R.layout.item_amigo, parent, false))
    }

    override fun onBindViewHolder(holder: AmigoViewHolder, position: Int) {
        val amigoItem = listAmigos[position]
        holder.render(amigoItem)
    }

    override fun getItemCount(): Int {
        return listAmigos.size
    }


}
