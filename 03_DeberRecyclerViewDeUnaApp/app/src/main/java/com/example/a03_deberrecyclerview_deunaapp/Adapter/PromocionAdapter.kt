package com.example.a03_deberrecyclerview_deunaapp.Adapter

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.a03_deberrecyclerview_deunaapp.Entity.Promocion
import com.example.a03_deberrecyclerview_deunaapp.R

class PromocionAdapter(val listPromociones: List<Promocion>) : RecyclerView.Adapter<PromocionAdapter.PromocionViewHolder>(){
    inner class PromocionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // ITEM PROMOCION
        val nombrePromocion = view.findViewById<TextView>(R.id.tv_)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PromocionViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: PromocionViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

}