package com.example.examen1bmoviles.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import com.example.examen1bmoviles.R
import com.example.examen1bmoviles.controler.ActualizacionRestaurante
import com.example.examen1bmoviles.controler.CreacionRestaurante
import com.example.examen1bmoviles.model.Restaurante
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ListViewRestaurante : AppCompatActivity() {

    var nombreRestaurante: String? = null
    private val db = FirebaseFirestore.getInstance()
    var adaptadorRestaurante: ArrayAdapter<Restaurante>? = null


    val arregloRestaurante: ArrayList<Restaurante> = arrayListOf()
    var idItemSeleccionado = 0
    var idRestaurante = ""


    // On create
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view_restaurante)

        // Inicializar adaptador

        val listViewRestaurante = findViewById<ListView>(R.id.lv_list_view_restaurante)
        adaptadorRestaurante = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            arregloRestaurante
        )

        listViewRestaurante.adapter = adaptadorRestaurante
        //adaptadorRestaurante.notifyDataSetChanged()


        // Obtener y mostrar los restaurantes desde Firestore
        registerForContextMenu(listViewRestaurante)
        adaptadorRestaurante!!.notifyDataSetChanged()


        // Ir a crear restaurante
        val botonInternoAniadirRestaurante =
            findViewById<Button>(R.id.btn_aniadir_externo_restaurante)
        botonInternoAniadirRestaurante.setOnClickListener {
            irActividadDesdeRestaurante(idRestaurante, CreacionRestaurante::class.java)
        }
    }

    override fun onResume() {
        super.onResume()
        consultarRestaurantes(adaptadorRestaurante!!)

        adaptadorRestaurante!!.notifyDataSetChanged()
    }

    private fun consultarRestaurantes(adaptadorRestaurante: ArrayAdapter<Restaurante>) {

        val db = Firebase.firestore
        val restaurantesRefUnico = db.collection("restaurantes")
        arregloRestaurante.clear()
        adaptadorRestaurante.notifyDataSetChanged()
        restaurantesRefUnico.orderBy("nombre").get().addOnSuccessListener {
            for (restaurante in it) {
                restaurante.id
                aniadirArregloRestaurante(restaurante)
            }
            adaptadorRestaurante.notifyDataSetChanged()
        }
            .addOnFailureListener {

            }
    }

    private fun aniadirArregloRestaurante(restaurante: QueryDocumentSnapshot?) {
        val restauranteNuevo = Restaurante(
                restaurante?.id,
        restaurante?.getString("nombre"),
        restaurante?.getString("direccion"),
        restaurante?.getString("ciudad"),
        restaurante?.getLong("michelin")?.toInt(),

        )
        arregloRestaurante.add(restauranteNuevo)
    }


    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        // llenar las opciones del menu
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_context_restaurante, menu)

        // obtener el id del ArrayList seleccionado
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        idItemSeleccionado = id
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        var idRestaurante = arregloRestaurante.get(idItemSeleccionado).id
        nombreRestaurante = arregloRestaurante.get(idItemSeleccionado).nombre


        return when (item.itemId) {
            R.id.menu_editar_res -> {
                irActividadDesdeRestaurante(idRestaurante, ActualizacionRestaurante::class.java)
                return true
            }

            R.id.menu_eliminar_res -> {
               eliminarRestaurante(idRestaurante!!)
                return true
            }

            R.id.menu_platillos_res -> {
                irActividadDesdeRestaurante(idRestaurante, ListViewPlatillo::class.java)
                return true
            }

            else -> super.onContextItemSelected(item)
        }
    }
    fun eliminarRestaurante(
        idRestaurante : String
    ) {
        var db = Firebase.firestore
        var restauranteRef = db.collection("restaurantes")
        restauranteRef
            .document(idRestaurante)
            .delete()
        consultarRestaurantes(adaptadorRestaurante!!)
        mostrarSnackBar("Restaurante eliminado correctamente")
    }

    fun irActividadDesdeRestaurante(
        idRestaurante: String?, clase: Class<*>
    ) {
        val intent = Intent(this, clase)
        intent.putExtra("nombreRestaurante", nombreRestaurante)
        intent.putExtra("idItemSeleccionado", idItemSeleccionado)
        intent.putExtra("idRestaurante", idRestaurante)

        startActivity(intent)
    }


    private fun mostrarSnackBar(mensaje: String) {
        val rootView = findViewById<View>(android.R.id.content)
        Snackbar.make(rootView, mensaje, Snackbar.LENGTH_SHORT).show()
    }
}