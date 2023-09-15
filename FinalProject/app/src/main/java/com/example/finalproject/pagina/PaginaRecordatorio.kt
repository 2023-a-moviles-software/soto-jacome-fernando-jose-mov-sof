package com.example.finalproject.pagina

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.MainActivity
import com.example.finalproject.R
import com.example.finalproject.adapter.RecordatorioAdapter
import com.example.finalproject.firestore.Provider

class PaginaRecordatorio : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recordatorio)
        initRecyclerRecordatorio()

        // Botón Main
        findViewById<Button>(R.id.btn_home2).setOnClickListener {
            irActividad(MainActivity::class.java)
        }
        // Boton Logros
        findViewById<Button>(R.id.btn_logro2).setOnClickListener {
            irActividad(PaginaLogros::class.java)
        }
        // Boton Profile
        findViewById<Button>(R.id.btn_perfil2).setOnClickListener {
            irActividad(PaginaUsuario::class.java)
        }


    }

    private fun initRecyclerRecordatorio() {
        val recyclerViewRecordatorio = findViewById<RecyclerView>(R.id.rv_recordatorio)
        recyclerViewRecordatorio.layoutManager = LinearLayoutManager(this)

        val provider = Provider()
        provider.listRecordatorioLiveData.observe(this) { listRecordatorio ->
            recyclerViewRecordatorio.adapter = RecordatorioAdapter(listRecordatorio)
        }
        provider.cargarRecordatorio()
    }


    fun irActividad(
        clase: Class<*>
    ) {
        val intent = Intent(this, clase)
        startActivity(intent)

    }
}