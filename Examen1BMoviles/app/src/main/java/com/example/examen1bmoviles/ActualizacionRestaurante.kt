package com.example.examen1bmoviles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class ActualizacionRestaurante : AppCompatActivity() {
    var idItemSeleccionadoRestaurante = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar_restaurante)

        idItemSeleccionadoRestaurante = intent.getIntExtra("idItemSeleccionado", 0)

        val botonActualizar = findViewById<Button>(R.id.btn_actualizar_platillo)
        botonActualizar.setOnClickListener {
            actualizarRestaurante()
        }
        mostrarInformacionRestaurante()

    }

    private fun actualizarRestaurante() {


        // Obtener los nuevos valores de los elementos de la interfaz de usuario
        val nuevoId = findViewById<EditText>(R.id.input_update_id_res)
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


        BaseDatosMemoria.arregloRestaurante[idItemSeleccionadoRestaurante] = restauranteActualizado
        ListViewRestaurante.adaptadorRestaurante.notifyDataSetChanged()

        finish()
    }

    private fun mostrarInformacionRestaurante() {
        val viejoRestaurante = BaseDatosMemoria.arregloRestaurante[idItemSeleccionadoRestaurante]

        // Se carga toda la información del restaurante seleccionado en la nueva vista
        val textViewSeleccionadoRes = findViewById<TextView>(R.id.txt_seleccionado_platillo)
        val inputUpdateId = findViewById<TextView>(R.id.input_update_id_res)
        val inputUpdateNombreRes = findViewById<TextView>(R.id.input_update_nombre_res)
        val inputUpdateDireccion = findViewById<TextView>(R.id.input_update_direccion)
        val inputUpdateCiudad = findViewById<TextView>(R.id.input_update_ciudad)
        val inputUpdateMichelin = findViewById<TextView>(R.id.input_update_michelin)


        textViewSeleccionadoRes.text = viejoRestaurante.nombre
        inputUpdateId.text = viejoRestaurante.id.toString()
        inputUpdateNombreRes.text = viejoRestaurante.nombre
        inputUpdateDireccion.text = viejoRestaurante.direccion
        inputUpdateCiudad.text = viejoRestaurante.ciudad
        inputUpdateMichelin.text = viejoRestaurante.michelin.toString()

    }
}


