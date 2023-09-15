package com.example.finalproject.pagina

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.MainActivity
import com.example.finalproject.R
import com.example.finalproject.adapter.LogroAdapter
import com.example.finalproject.firestore.Provider

class PaginaLogros : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logros)
        initRecyclerLogros()

        // Botón Main
        findViewById<Button>(R.id.btn_home3).setOnClickListener {
            irActividad(MainActivity::class.java)
        }
        // Boton Recordatorio
        findViewById<Button>(R.id.btn_calendar3).setOnClickListener {
            irActividad(PaginaRecordatorio::class.java)
        }
        // Boton Profile
        findViewById<Button>(R.id.btn_perfil3).setOnClickListener {
            irActividad(PaginaUsuario::class.java)
        }

    }

    private fun initRecyclerLogros() {

        val recyclerViewLogros = findViewById<RecyclerView>(R.id.rv_logros)
        recyclerViewLogros.layoutManager = LinearLayoutManager(this)

        val provider = Provider()
        provider.listLogrosLiveData.observe(this) { listLogros ->
            recyclerViewLogros.adapter = LogroAdapter(listLogros)
        }
        provider.cargarLogros()

    }
    fun irActividad(
        clase: Class<*>
    ) {
        val intent = Intent(this, clase)
        startActivity(intent)

    }
}