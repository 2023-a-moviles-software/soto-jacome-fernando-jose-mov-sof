package com.example.a03_deberrecyclerview_deunaapp.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.a03_deberrecyclerview_deunaapp.R
import com.example.a03_deberrecyclerview_deunaapp.Entity.Cuenta

class CuentaAdapter(val cuentaList: List<Cuenta>) :
    RecyclerView.Adapter<CuentaAdapter.CuentaviewHolder>() {


    inner class CuentaviewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nombreCuenta = view.findViewById<TextView>(R.id.tv_nombreCuenta)
        val numeroCuenta = view.findViewById<TextView>(R.id.tv_numeroCuenta)
        val saldo = view.findViewById<TextView>(R.id.tv_saldo)
        val imagenPichincha = view.findViewById<ImageView>(R.id.iv_pichincha)
        val imagenEstrella = view.findViewById<ImageView>(R.id.iv_star)

        fun render(cuenta: Cuenta) {
            nombreCuenta.text = cuenta.nombreCuenta
            numeroCuenta.text = cuenta.numeroCuenta
            saldo.text = "$ ${cuenta.saldo}"
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CuentaviewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CuentaviewHolder(layoutInflater.inflate(R.layout.item_cuenta, parent, false))
    }

    override fun onBindViewHolder(holder: CuentaviewHolder, position: Int) {
        val cuentaItem = cuentaList[position]
        holder.imagenPichincha.setImageResource(cuentaItem.imagenPichincha)
        holder.imagenEstrella.setImageResource(cuentaItem.imagenEstrella)
        holder.render(cuentaItem)
    }

    override fun getItemCount(): Int = cuentaList.size
}