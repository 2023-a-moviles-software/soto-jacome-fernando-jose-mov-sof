package com.example.examen1bmoviles

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class ActualizarRestaurante : AppCompatActivity() {
    var idItemSeleccionado = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar_restaurante)

        idItemSeleccionado = intent.getIntExtra("idItemSeleccionado",0)

        val botonActualizar = findViewById<Button>(R.id.btn_actualizar_res)
        botonActualizar.setOnClickListener {
            actualizarRestaurante()
        }
        mostrarNombreViejoRestaurante()

    }

    private fun actualizarRestaurante() {


        // Obtener los nuevos valores de los elementos de la interfaz de usuario
        val nuevoId = findViewById<EditText>(R.id.input_update_id)
        val nuevoNombreRestaurante = findViewById<EditText>(R.id.input_update_nombre_res)
        val nuevaDireccion = findViewById<EditText>(R.id.input_update_direccion)
        val nuevaCiudad = findViewById<EditText>(R.id.input_update_ciudad)
        val nuevaMichelin = findViewById<EditText>(R.id.input_update_michelin)

        // Crear el objeto Restaurante actualizado
        val restauranteActualizado = Restaurante(
            nuevoId.text.toString().toInt(),
            nuevoNombreRestaurante.text.toString(),
            nuevaDireccion.text.toString(),
            nuevaCiudad.text.toString(),
            nuevaMichelin.text.toString().toInt()
        )


        BaseDatosMemoria.arregloRestaurante[idItemSeleccionado] = restauranteActualizado
        ListViewRestaurante.adaptador.notifyDataSetChanged()

        finish()
    }

    private fun mostrarNombreViejoRestaurante(){
        val selectObjRestaurante = BaseDatosMemoria.arregloRestaurante[idItemSeleccionado]
        val selectTextoRestaurante =findViewById<TextView>(R.id.txt_seleccionado_res)

        selectTextoRestaurante.text = selectObjRestaurante.nombre.toString()
    }
}


