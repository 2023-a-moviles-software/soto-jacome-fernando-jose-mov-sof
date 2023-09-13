package com.example.examen1bmoviles.controler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import com.example.examen1bmoviles.BaseDatosMemoria
import com.example.examen1bmoviles.view.ListViewRestaurante
import com.example.examen1bmoviles.R
import com.example.examen1bmoviles.model.Restaurante
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore

class CreacionRestaurante : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_creacion_restaurante)

        val botonAniadirRestauranteFire =
            findViewById<Button>(R.id.btn_aniadir_rest_fire)
        botonAniadirRestauranteFire.setOnClickListener {
            crearRestFire()
        }
    }

    private fun crearRestFire() {



        // Validar que los datos sean correctos antes de continuar
        if (validarDatos()) {

            val nombreRestaurante = findViewById<EditText>(R.id.input_nombre_res).text.toString()
            val direccion = findViewById<EditText>(R.id.input_direccion).text.toString()
            val ciudad = findViewById<EditText>(R.id.input_ciudad).text.toString()
            val michelin = findViewById<EditText>(R.id.input_michelin).text.toString().toInt()


            val nuevoRestaurante = hashMapOf(
                "nombre" to nombreRestaurante,
                "direccion" to direccion,
                "ciudad" to ciudad,
                "michelin" to michelin
            )

            // Obtener una referencia a la colección "restaurantes" en Firestore
            val restaurantesRef = db.collection("restaurantes")

            // Añadir el nuevo restaurante a la colección
            restaurantesRef
                .add(nuevoRestaurante)
                .addOnSuccessListener { documentReference ->
                    mostrarSnackBar("Restaurante creado exitosamente con ID: ${documentReference.id}")
                    finish()
                }
                .addOnFailureListener { e ->
                    mostrarSnackBar("Error al crear el restaurante: $e")
                }
        } else {
            mostrarSnackBar("Formato de datos ingresados incorrectos")
        }
    }


    private fun validarDatos(): Boolean {

        val nombreRestaurante = findViewById<EditText>(R.id.input_nombre_res).text.toString()
        val direccion = findViewById<EditText>(R.id.input_direccion).text.toString()
        val ciudad = findViewById<EditText>(R.id.input_ciudad).text.toString()
        val michelin = findViewById<EditText>(R.id.input_ciudad).text.toString()



        // Validación input vacío
        if (nombreRestaurante.isEmpty() || direccion.isEmpty() || ciudad.isEmpty() || michelin.isEmpty()) {
            return false
        }


        if (michelin.toIntOrNull() == null) {
            return false
        }
        return true
    }


    private fun mostrarSnackBar(mensaje: String) {
        val rootView = findViewById<View>(android.R.id.content)
        Snackbar.make(rootView, mensaje, Snackbar.LENGTH_SHORT).show()
    }


}


