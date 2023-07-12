package com.example.examen1bmoviles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
        mostrarInformacionViejoRestaurante()

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

    private fun mostrarInformacionViejoRestaurante(){
        val viejoRestaurante = BaseDatosMemoria.arregloRestaurante[idItemSeleccionado]

        // Se carga toda la información del restaurante seleccionado en la nueva vista
        findViewById<TextView>(R.id.txt_seleccionado_res).text = viejoRestaurante.nombre
        findViewById<TextView>(R.id.input_update_id).text = viejoRestaurante.id.toString()
        findViewById<TextView>(R.id.input_update_nombre_res).text = viejoRestaurante.nombre
        findViewById<TextView>(R.id.input_update_direccion).text = viejoRestaurante.direccion
        findViewById<TextView>(R.id.input_update_ciudad).text = viejoRestaurante.ciudad
        findViewById<TextView>(R.id.input_update_michelin).text = viejoRestaurante.michelin.toString()
    }
}


