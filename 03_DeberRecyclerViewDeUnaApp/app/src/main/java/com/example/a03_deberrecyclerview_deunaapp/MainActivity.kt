package com.example.a03_deberrecyclerview_deunaapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.a03_deberrecyclerview_deunaapp.Adapter.CuentaAdapter
import com.example.a03_deberrecyclerview_deunaapp.Adapter.PromocionAdapter
import com.example.a03_deberrecyclerview_deunaapp.Provider.RecyclerProvider

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerCuenta()
        setSnapHelper()
        initRecyclerPromocion()
    }

    private fun initRecyclerCuenta(){
        val recyclerViewCuenta = findViewById<RecyclerView>(R.id.rv_cuentas)
        recyclerViewCuenta.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewCuenta.adapter = CuentaAdapter(RecyclerProvider.listCuentas)
    }

    private fun setSnapHelper() {
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(findViewById(R.id.rv_cuentas))
    }

    private fun initRecyclerPromocion(){
        val recyclerViewPromocion = findViewById<RecyclerView>(R.id.rv_promociones)
        recyclerViewPromocion.layoutManager = LinearLayoutManager(this)
        recyclerViewPromocion.adapter = PromocionAdapter(RecyclerProvider.listPromociones)
    }


}
