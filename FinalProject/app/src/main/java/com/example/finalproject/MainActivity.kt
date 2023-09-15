package com.example.finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.adapter.ActividadAdapter
import com.example.finalproject.firestore.Provider
import com.example.finalproject.pagina.PaginaAmigo
import com.example.finalproject.pagina.PaginaLogros
import com.example.finalproject.pagina.PaginaUsuario
import com.example.finalproject.pagina.PaginaRecordatorio
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    val auth = FirebaseAuth.getInstance()
    val user = auth.currentUser
    val userId = user?.uid

    override fun onCreate(savedInstanceState: Bundle?) {
       super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerActividad()
        setSnapHelper()


        val botonCalendar = findViewById<Button>(
            R.id.btn_calendar
        )
        botonCalendar.setOnClickListener {
            irActividad(PaginaRecordatorio::class.java)
        }

        val botonLogro = findViewById<Button>(
            R.id.btn_logro
        )
        botonLogro.setOnClickListener {
            irActividad(PaginaLogros::class.java)
        }

        val botonAmigo = findViewById<Button>(
            R.id.btn_amigo
        )
        botonAmigo.setOnClickListener {
            irActividad(PaginaAmigo::class.java)
        }

        val botonPerfil = findViewById<Button>(
            R.id.btn_perfil
        )
        botonPerfil.setOnClickListener {
            irActividad(PaginaUsuario::class.java)
        }


    }

    fun irActividad(
        clase: Class<*>
    ) {
        val intent = Intent(this, clase)
        // NO RECIBIMOS RESPUESTA
        startActivity(intent)

    }

    // 1. ACTIVIDAD
    private fun initRecyclerActividad() {
        val recyclerViewActividad = findViewById<RecyclerView>(R.id.rv_actividad)
        recyclerViewActividad.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val provider = Provider()
        provider.listActividadLiveData.observe(this) { listActividad ->
            recyclerViewActividad.adapter = ActividadAdapter(listActividad)
        }
        provider.cargarActividades()
    }

    private fun setSnapHelper() {
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(findViewById(R.id.rv_actividad))
    }

}