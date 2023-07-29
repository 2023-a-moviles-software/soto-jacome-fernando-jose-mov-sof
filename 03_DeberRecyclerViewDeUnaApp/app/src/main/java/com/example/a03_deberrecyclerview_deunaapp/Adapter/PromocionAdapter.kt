package com.example.a03_deberrecyclerview_deunaapp.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.a03_deberrecyclerview_deunaapp.Entity.Promocion
import com.example.a03_deberrecyclerview_deunaapp.R
import org.w3c.dom.Text

class PromocionAdapter(val listPromociones: List<Promocion>) : RecyclerView.Adapter<PromocionAdapter.PromocionViewHolder>(){
    inner class PromocionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // ITEM PROMOCION
        val urlImgPromocion = view.findViewById<ImageView>(R.id.iv_imagenPromocion)
        val nombrePromocion = view.findViewById<TextView>(R.id.tv_nombrePromocion)
        val descripcoin = view.findViewById<TextView>(R.id.tv_descripcion)

        fun render(promocion: Promocion){
            Glide.with(urlImgPromocion.context).load(promocion.imagenPromocion).into(urlImgPromocion)
            nombrePromocion.text = promocion.nombrePromocion
            descripcoin.text = promocion.descripcion
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PromocionViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PromocionViewHolder(layoutInflater.inflate(R.layout.item_promocion, parent, false))
    }

    override fun getItemCount(): Int = listPromociones.size

    override fun onBindViewHolder(holder: PromocionViewHolder, position: Int) {
      val promocionItem = listPromociones[position]
        holder.render(promocionItem)
    }

}