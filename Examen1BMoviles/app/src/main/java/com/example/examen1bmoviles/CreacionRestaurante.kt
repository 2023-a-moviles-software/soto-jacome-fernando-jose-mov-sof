package com.example.examen1bmoviles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import com.google.android.material.snackbar.Snackbar

class CreacionRestaurante : AppCompatActivity() {
    private var ultimoIdRestaurante = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_creacion_restaurante)


        // Conseguir el último objeto del arreglo restaurante
        if (BaseDatosMemoria.arregloRestaurante.isNotEmpty()) {
            ultimoIdRestaurante = BaseDatosMemoria.arregloRestaurante.last().id
        }

        val botonAniadirListViewRestaurante =
            findViewById<Button>(R.id.btn_aniadir_interno_restaurante)
        botonAniadirListViewRestaurante.setOnClickListener {

            if (validarDatos()) {
                crearRestaurante(ListViewRestaurante.adaptadorRestaurante)
            } else {
                mostrarSnackBar("Formato de datos ingresados incorrectos")
            }


        }

    }

    private fun validarDatos(): Boolean {

        val nombreRestaurante = findViewById<EditText>(R.id.input_nombre_res).text.toString()
        val direccion = findViewById<EditText>(R.id.input_direccion).text.toString()
        val ciudad = findViewById<EditText>(R.id.input_ciudad).text.toString()
        val michelin = findViewById<EditText>(R.id.input_michelin).text.toString()

        // Validación input vacío
        if (nombreRestaurante.isEmpty() || direccion.isEmpty() || ciudad.isEmpty() || michelin.isEmpty()) {
            return false
        }

        // Validación formato incorrecto

        if (michelin.toIntOrNull() == null) {
            return false
        }

        return true
    }


    private fun mostrarSnackBar(mensaje: String) {
        val rootView = findViewById<View>(android.R.id.content)
        Snackbar.make(rootView, mensaje, Snackbar.LENGTH_SHORT).show()
    }

    fun crearRestaurante(
        adaptador: ArrayAdapter<Restaurante>
    ) {
        // Generando id automático
        val nuevoIdRestaurante = ultimoIdRestaurante + 1


        val nombreRestaurante = findViewById<EditText>(R.id.input_nombre_res)
        val direccion = findViewById<EditText>(R.id.input_direccion)
        val ciudad = findViewById<EditText>(R.id.input_ciudad)
        val michelin = findViewById<EditText>(R.id.input_michelin)
        BaseDatosMemoria.arregloRestaurante.add(

            Restaurante(
                nuevoIdRestaurante,
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