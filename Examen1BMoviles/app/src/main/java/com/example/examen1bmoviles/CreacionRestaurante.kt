package com.example.examen1bmoviles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText

class CreacionRestaurante : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_creacion_restaurante)

        val botonAniadirListViewRestaurante = findViewById<Button>(R.id.btn_aniadir_interno_restaurante)
        botonAniadirListViewRestaurante.setOnClickListener {
            crearRestaurante(ListViewRestaurante.adaptadorRestaurante)
        }

    }
    fun crearRestaurante(
        adaptador: ArrayAdapter<Restaurante>
    ) {
        val id = findViewById<EditText>(R.id.input_id_res)
        val nombreRestaurante = findViewById<EditText>(R.id.input_nombre_res)
        val direccion = findViewById<EditText>(R.id.input_direccion)
        val ciudad = findViewById<EditText>(R.id.input_ciudad)
        val michelin = findViewById<EditText>(R.id.input_michelin)
        BaseDatosMemoria.arregloRestaurante.add(

            Restaurante(
                id.text.toString().toInt(),
                nombreRestaurante.text.toString(),
                direccion.text.toString(),
                ciudad.text.toString(),
                michelin.text.toString().toInt()
            )
        )
        adaptador.notifyDataSetChanged()


        finish()
    }
}