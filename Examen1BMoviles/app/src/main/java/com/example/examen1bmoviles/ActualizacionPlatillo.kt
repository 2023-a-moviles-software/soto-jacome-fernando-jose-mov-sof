package com.example.examen1bmoviles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class ActualizacionPlatillo : AppCompatActivity() {
    var idItemSeleccionadoPlatillo = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizacion_platillo)


        idItemSeleccionadoPlatillo = intent.getIntExtra("idItemSeleccionadoPlatillo",0)

        Log.d("s", "Estoy en onCreateACtualización Platillo: ${idItemSeleccionadoPlatillo} AAAAAAAAAAAAAAAAAAAA")

        val botonActualizarPlatillo = findViewById<Button>(R.id.btn_actualizar_platillo)
        botonActualizarPlatillo.setOnClickListener{
            actualizarPlatillo()
        }
        mostrarInformacionPlatillo()



    }

    private fun actualizarPlatillo() {
        var  viejoPlatillo = ListViewPlatillo.subArregloPlatilloXRest[idItemSeleccionadoPlatillo]

        // Obtener los nuevos valores de los elementos de la interfaz de usuario
        val nuevoNombrePlatillo = findViewById<EditText>(R.id.input_update_nombre_platillo)
        val nuevaDescripcion = findViewById<EditText>(R.id.input_update_descripcion)
        val nuevoPrecio = findViewById<EditText>(R.id.input_update_precio)


        // Crear el objeto Restaurante actualizado
        val platilloActualizado = Platillo(
            viejoPlatillo.id,
            nuevoNombrePlatillo.text.toString(),
            nuevaDescripcion.text.toString(),
            nuevoPrecio.text.toString().toDouble(),
            viejoPlatillo.restauranteId

        )


       // Actualizar el subarreglo
        ListViewPlatillo.subArregloPlatilloXRest[idItemSeleccionadoPlatillo] = platilloActualizado


        // Modificar el arreglo general de platillos
        val index = BaseDatosMemoria.arregloPlatillo.indexOfFirst { it.id == viejoPlatillo.id }
        BaseDatosMemoria.arregloPlatillo[index] = platilloActualizado

        // Acutalizar el listView platillos
        ListViewPlatillo.adaptadorPlatillo.notifyDataSetChanged()

        finish()
    }

    private fun mostrarInformacionPlatillo() {

        var  viejoPlatillo = ListViewPlatillo.subArregloPlatilloXRest[idItemSeleccionadoPlatillo]

        val inputUpdateNombre = findViewById<TextView>(R.id.input_update_nombre_platillo)
        val inputUpdateDescripcion = findViewById<TextView>(R.id.input_update_descripcion)
        val inputUpdatePrecio = findViewById<TextView>(R.id.input_update_precio)
        val txtInputSelectPlatillo = findViewById<TextView>(R.id.txt_seleccionado_platillo)



        txtInputSelectPlatillo.text = viejoPlatillo.nombre
        inputUpdateNombre.text = viejoPlatillo.nombre
        inputUpdateDescripcion.text = viejoPlatillo.descripcion
        inputUpdatePrecio.text = viejoPlatillo.precio.toString()
    }
}