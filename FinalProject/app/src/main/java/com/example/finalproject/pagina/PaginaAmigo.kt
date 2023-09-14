package com.example.finalproject.pagina

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.R
import com.example.finalproject.adapter.AmigoAdapter
import com.example.finalproject.firestore.Provider

class PaginaAmigo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_amigo)
        initRecyclerAmigos()
    }

    private fun initRecyclerAmigos() {

        val recyclerViewAmigos = findViewById<RecyclerView>(R.id.rv_amigo)
        recyclerViewAmigos.layoutManager = LinearLayoutManager(this)

        val provider = Provider()
        provider.listAmigosLiveData.observe(this) { listAmigos ->
            recyclerViewAmigos.adapter = AmigoAdapter(listAmigos)
        }
        provider.cargarAmigos()

    }
}