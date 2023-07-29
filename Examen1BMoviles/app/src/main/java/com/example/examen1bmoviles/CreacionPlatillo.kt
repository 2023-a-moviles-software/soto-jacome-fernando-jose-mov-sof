package com.example.examen1bmoviles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar

class CreacionPlatillo : AppCompatActivity() {
    var restauranteId = 0
    var idItemSeleccionado = 0
    private var ultimoIdPlatillo = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_creacion_platillo)

        idItemSeleccionado = intent.getIntExtra("idItemSeleccionado", idItemSeleccionado)
        restauranteId = intent.getIntExtra("restauranteId", 0)

        // Conseguir el último objeto platillo
        if (BaseDatosMemoria.arregloPlatillo.isNotEmpty()) {
            ultimoIdPlatillo = BaseDatosMemoria.arregloPlatillo.last().id
        }

        val botonAniadir = findViewById<Button>(R.id.btn_aniadir_platillo_interno)
        botonAniadir.setOnClickListener {
            if (validarDatos()) {
                crearPlatillo()
            } else {
                mostrarSnackBar("Formato de datos ingresados incorrectos")
            }


        }
        mostrarNombreRestaurante()

    }

    private fun validarDatos(): Boolean {
        val nombrePlatillo = findViewById<TextView>(R.id.input_nombre_platillo).text.toString()
        val descripcion = findViewById<TextView>(R.id.input_descripcion).text.toString()
        val precio = findViewById<TextView>(R.id.input_precio).text.toString()

        // Validación input vacío
        if (nombrePlatillo.isEmpty() || descripcion.isEmpty() || precio.isEmpty()) {
            return false
        }
        // Validación formato incorrecto
        if (precio.toIntOrNull() == null) {
            return false
        }
        return true
    }
    private fun mostrarSnackBar(mensaje: String) {
        val rootView = findViewById<TextView>(R.id.txt_nombre_res_interno)
        Snackbar.make(rootView, mensaje, Snackbar.LENGTH_SHORT).show()
    }
    fun crearPlatillo() {

        val nuevoIdPlatillo = ultimoIdPlatillo + 1

        val nombrePlatillo = findViewById<TextView>(R.id.input_nombre_platillo)
        val descripcion = findViewById<TextView>(R.id.input_descripcion)
        val precio = findViewById<TextView>(R.id.input_precio)

        BaseDatosMemoria.arregloPlatillo.add(
            Platillo(
                nuevoIdPlatillo,
                nombrePlatillo.text.toString(),
                descripcion.text.toString(),
                precio.text.toString().toDouble(),
                restauranteId
            )
        )


        ListViewPlatillo.adaptadorPlatillo.notifyDataSetChanged()
        finish()


    }

    fun mostrarNombreRestaurante() {
        val textViewRestaurante = findViewById<TextView>(R.id.txt_nombre_res_interno)
        textViewRestaurante.text =
            BaseDatosMemoria.arregloRestaurante[idItemSeleccionado].nombre
    }

}