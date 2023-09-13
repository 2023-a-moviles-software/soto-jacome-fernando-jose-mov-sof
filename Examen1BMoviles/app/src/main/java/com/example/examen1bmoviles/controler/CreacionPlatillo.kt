package com.example.examen1bmoviles.controler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.examen1bmoviles.BaseDatosMemoria
import com.example.examen1bmoviles.view.ListViewPlatillo
import com.example.examen1bmoviles.R
import com.example.examen1bmoviles.model.Platillo
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CreacionPlatillo : AppCompatActivity() {
    var idRestaurante = String?:""
    var idItemSeleccionado = 0
   var nombreRestaurante = String?:""
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_creacion_platillo)

        idRestaurante = intent.getStringExtra("idRestaurante")!!
        nombreRestaurante = intent.getStringExtra("nombreRestaurante")!!

        findViewById<TextView>(R.id.txt_nombre_res_interno).text = nombreRestaurante as String

        val botonAniadir = findViewById<Button>(R.id.btn_aniadir_platillo_interno)
        botonAniadir.setOnClickListener {
            if (validarDatos()) {
                crearPlatillo()
                finish()
            } else {
                mostrarSnackBar("Formato de datos ingresados incorrectos")
            }
        }

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

    private fun crearPlatillo() {



        if (validarDatos()) {
            val nombrePlatillo = findViewById<EditText>(R.id.input_nombre_platillo).text.toString()
            val descripcion = findViewById<EditText>(R.id.input_descripcion).text.toString()
            val precio = findViewById<EditText>(R.id.input_precio).text.toString().toDouble()


            val nuevoPlatillo = hashMapOf(
                "nombre" to nombrePlatillo,
                "descripcion" to descripcion,
                "precio" to precio
            )


            val platillosRef = db.collection("restaurantes").document(idRestaurante.toString()).collection("platillos")

            // Añadir el nuevo platillo a la colección
            platillosRef
                .add(nuevoPlatillo)
                .addOnSuccessListener { documentReference ->
                    mostrarSnackBar("Platillo creado exitosamente con ID: ${documentReference.id}")
                    finish()
                }
                .addOnFailureListener { e ->
                    mostrarSnackBar("Error al crear el platillo: $e")
                }
        } else {
            mostrarSnackBar("Formato de datos ingresados incorrectos")
        }
    }


    fun mostrarNombreRestaurante() {
        val textViewRestaurante = findViewById<TextView>(R.id.txt_nombre_res_interno)
        textViewRestaurante.text =
            BaseDatosMemoria.arregloRestaurante[idItemSeleccionado].nombre
    }

}