package com.example.examen1bmoviles.controler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.examen1bmoviles.BaseDatosMemoria
import com.example.examen1bmoviles.view.ListViewPlatillo
import com.example.examen1bmoviles.R
import com.example.examen1bmoviles.model.Platillo
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ActualizacionPlatillo : AppCompatActivity() {
    var idRestaurante: String? = ""
    var idPlatillo: String? = ""
    var nombrePlatillo: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizacion_platillo)


        idRestaurante = intent.getStringExtra("idRestaurante")
        idPlatillo = intent.getStringExtra("idPlatillo")
        nombrePlatillo = intent.getStringExtra("nombrePlatillo")

        findViewById<TextView>(R.id.txt_seleccionado_platillo).setText(nombrePlatillo)

        consultarPlatilloViejo(idRestaurante!!, idPlatillo!!)

        val botonActualizarPlatillo = findViewById<Button>(R.id.btn_actualizar_platillo)

        botonActualizarPlatillo.setOnClickListener {
            actualizarPlatillo()
            finish()
        }
    }

    fun consultarPlatilloViejo(idRestaurante : String, idPlatillo : String){
        val db = Firebase.firestore
        val platillosReferencia = db.collection("restaurantes").document(idRestaurante).
            collection("platillos").document(idPlatillo)
        platillosReferencia
            .get()
            .addOnSuccessListener {
                findViewById<EditText>(R.id.input_update_nombre_platillo).setText(it.data?.get("nombre") as String?)
                findViewById<EditText>(R.id.input_update_descripcion).setText(it.data?.get("descripcion") as String?)
                findViewById<EditText>(R.id.input_update_precio).setText((it.data?.get("precio") as Double?).toString())
            }
    }


    private fun actualizarPlatillo() {
        val inputUpdateNombre = findViewById<TextView>(R.id.input_update_nombre_platillo).text.toString()
        val inputUpdateDescripcion = findViewById<TextView>(R.id.input_update_descripcion).text.toString()
        val inputUpdatePrecio = findViewById<TextView>(R.id.input_update_precio).text.toString().toDouble()
            var db = Firebase.firestore
            var frutasReferencia =
                db.
                collection("restaurantes").document(idRestaurante!!).
                collection("platillos").document(idPlatillo!!)

            frutasReferencia.set(
                hashMapOf(
                    "nombre" to inputUpdateNombre,
                    "descripcion" to inputUpdateDescripcion,
                    "precio" to inputUpdatePrecio,
                )
            )


    }

}