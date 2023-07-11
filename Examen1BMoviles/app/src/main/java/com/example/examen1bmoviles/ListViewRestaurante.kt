package com.example.examen1bmoviles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView

class ListViewRestaurante : AppCompatActivity() {
    val arregloRestaurante = BaseDatosMemoria.arregloRestaurante


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view_restaurante)

        // Adaptador
       val listViewRestaurante = findViewById<ListView>(R.id.lv_list_view_restaurante)
        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            arregloRestaurante
        )
        listViewRestaurante.adapter = adaptador
        adaptador.notifyDataSetChanged()

        val botonAniadirListViewRestaurante = findViewById<Button>(R.id.btn_aniadir_list_view)
        botonAniadirListViewRestaurante.setOnClickListener {
            anadirRestaurante(adaptador)
        }

        registerForContextMenu(listViewRestaurante)

    }

    fun anadirRestaurante(
        adaptador: ArrayAdapter<Restaurante>
    ){
        val id = findViewById<EditText>(R.id.input_id).toString()
        val nombreRestaurante = findViewById<EditText>(R.id.input_nombre).toString()
        val direccion = findViewById<EditText>(R.id.input_direccion).toString()
        val ciudad = findViewById<EditText>(R.id.input_ciudad).toString()
        val michelin = findViewById<EditText>(R.id.input_michelin).toString()
        arregloRestaurante.add(

            Restaurante(id.toInt(), nombreRestaurante, direccion, ciudad, michelin.toInt())
        )
        adaptador.notifyDataSetChanged()
    }



}