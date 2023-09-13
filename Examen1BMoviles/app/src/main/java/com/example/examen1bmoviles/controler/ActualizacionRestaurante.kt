package com.example.examen1bmoviles.controler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.examen1bmoviles.BaseDatosMemoria
import com.example.examen1bmoviles.R
import com.example.examen1bmoviles.model.Restaurante
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ActualizacionRestaurante : AppCompatActivity() {
    var idItemSeleccionadoRestaurante = 0
    var idRestaurante =""
    var nombreRestaurante: String?=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar_restaurante)

        idItemSeleccionadoRestaurante = intent.getIntExtra("idItemSeleccionado", 0)
        idRestaurante = intent.getStringExtra("idRestaurante").toString()
        nombreRestaurante = intent.getStringExtra("nombreRestaurante").toString()


        findViewById<TextView>(R.id.txt_seleccionado_rest).setText(nombreRestaurante)
        consultarRestauranteViejo(idRestaurante)
        val botonActualizar = findViewById<Button>(R.id.btn_actualizar_platillo)
        botonActualizar.setOnClickListener {
            actualizarRestaurante()
            finish()
        }
    }
    fun consultarRestauranteViejo(idRestaurante : String){
        val db = Firebase.firestore
        val restauranteReferencia = db.collection("restaurantes")
        restauranteReferencia
            .document(idRestaurante)
            .get()
            .addOnSuccessListener {

                findViewById<EditText>(R.id.input_update_nombre_res).setText(it.data?.get("nombre") as String?)
                findViewById<EditText>(R.id.input_update_direccion).setText(it.data?.get("direccion") as String?)
                findViewById<EditText>(R.id.input_update_ciudad).setText(it.data?.get("ciudad") as String?)
                findViewById<EditText>(R.id.input_update_michelin).setText((it.data?.get("michelin") as Number?).toString())

            }
    }

    private fun actualizarRestaurante() {
        var db = Firebase.firestore

        val nuevoNombreRestaurante = findViewById<EditText>(R.id.input_update_nombre_res).text.toString()
        val nuevaDireccion = findViewById<EditText>(R.id.input_update_direccion).text.toString()
        val nuevaCiudad = findViewById<EditText>(R.id.input_update_ciudad).text.toString()
        val nuevaMichelin = findViewById<EditText>(R.id.input_update_michelin).text.toString().toInt()

        var restaurantesRef = db.collection("restaurantes").document(idRestaurante)
        restaurantesRef.set(
            hashMapOf(
                "nombre" to nuevoNombreRestaurante,
                "direccion" to nuevaDireccion,
                "ciudad" to nuevaCiudad,
                "michelin" to nuevaMichelin
            )
        )
    }


}


