package com.example.finalproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.adapter.ActividadAdapter
import com.example.finalproject.firestore.RecyclerProvider

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerActividad()
        setSnapHelper()
    }


    // 1. ACTIVIDAD
    private fun initRecyclerActividad() {
        val recyclerViewActividad = findViewById<RecyclerView>(R.id.rv_actividad)
        recyclerViewActividad.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val provider = RecyclerProvider()
        provider.listActividadLiveData.observe(this) { listActividad ->
            recyclerViewActividad.adapter = ActividadAdapter(listActividad)
        }
        provider.cargarActividades()
    }
    private fun setSnapHelper() {
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(findViewById(R.id.rv_actividad))
    }

    // 2. EVENTOS
}