package com.example.examen1bmoviles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class CreacionPlatillo : AppCompatActivity() {
    var restauranteId = 0
    var idItemSeleccionado = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_creacion_platillo)

        idItemSeleccionado = intent.getIntExtra("idItemSeleccionado", idItemSeleccionado)
        restauranteId = intent.getIntExtra("restauranteId", 0)



        val botonAniadir = findViewById<Button>(R.id.btn_aniadir_platillo_interno)
        botonAniadir.setOnClickListener {
            crearPlatillo()
            finish()
        }
        mostrarNombreRestaurante()

    }

    fun crearPlatillo() {

        val nombrePlatillo = findViewById<TextView>(R.id.input_nombre_platillo)
        val descripcion = findViewById<TextView>(R.id.input_descripcion)
        val precio = findViewById<TextView>(R.id.input_precio)

        BaseDatosMemoria.arregloPlatillo.add(
            Platillo(
                asignarIdPlatillo(),
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

    private fun asignarIdPlatillo() : Int{
        val idMax = BaseDatosMemoria.arregloPlatillo.maxByOrNull { platillo -> platillo.id }?.id ?: 0
        return idMax + 1
    }
}