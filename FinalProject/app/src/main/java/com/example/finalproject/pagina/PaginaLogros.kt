package com.example.finalproject.pagina

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.R
import com.example.finalproject.adapter.LogroAdapter
import com.example.finalproject.adapter.RecordatorioAdapter
import com.example.finalproject.firestore.RecyclerProvider

class PaginaLogros : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logros)
        initRecyclerLogros()
    }

    private fun initRecyclerLogros() {

        val recyclerViewLogros = findViewById<RecyclerView>(R.id.rv_logros)
        recyclerViewLogros.layoutManager = LinearLayoutManager(this)

        val provider = RecyclerProvider()
        provider.listLogrosLiveData.observe(this) { listLogros ->
            recyclerViewLogros.adapter = LogroAdapter(listLogros)
        }
        provider.cargarLogros()

    }
}