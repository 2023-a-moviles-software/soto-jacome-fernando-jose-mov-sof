package com.example.finalproject.pagina

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.R
import com.example.finalproject.adapter.RecordatorioAdapter
import com.example.finalproject.firestore.Provider

class PaginaRecordatorio : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recordatorio)
        initRecyclerRecordatorio()
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
}