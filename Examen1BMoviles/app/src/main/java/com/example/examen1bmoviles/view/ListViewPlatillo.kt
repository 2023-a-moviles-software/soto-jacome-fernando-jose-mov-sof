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
import android.widget.TextView
import com.example.examen1bmoviles.R
import com.example.examen1bmoviles.controler.ActualizacionPlatillo
import com.example.examen1bmoviles.controler.CreacionPlatillo
import com.example.examen1bmoviles.model.Platillo
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ListViewPlatillo : AppCompatActivity() {

    var idItemSeleccionadoPlatillo = 0
    var arregloPlatillo : ArrayList<Platillo> = arrayListOf()
    var adaptadorPlatillo : ArrayAdapter<Platillo>? = null


    var idRestaurante : String? = ""
    var idPlatillo : String? = ""
    var nombreRestaurante : String? = ""
    var nombrePlatillo : String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view_platillos)

        // Se recupera las variables enviadas en la anterior vista
        idRestaurante = intent.getStringExtra("idRestaurante")
       nombreRestaurante = intent.getStringExtra("nombreRestaurante")



        // Inicializar adaptador
        val listViewPlatillo = findViewById<ListView>(R.id.lv_list_view_platillo)
        adaptadorPlatillo = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            arregloPlatillo!!
        )

        listViewPlatillo.adapter = adaptadorPlatillo

        // Muestra el nombre del restaurante
        findViewById<TextView>(R.id.txt_nombre_res_platillo).setText(nombreRestaurante)



        // Aniadir un nuevo platillo
        val botonAniadirPlatillo = findViewById<Button>(
            R.id.btn_aniadir_platillo
        )
        botonAniadirPlatillo.setOnClickListener {
            irAaniadirPlatillo(CreacionPlatillo::class.java)
            adaptadorPlatillo!!.notifyDataSetChanged()
        }

        consultarPlatillos(adaptadorPlatillo!!, idRestaurante!!)
        adaptadorPlatillo!!.notifyDataSetChanged()

        registerForContextMenu(listViewPlatillo)
    }

    override fun onResume() {
        super.onResume()

        consultarPlatillos(adaptadorPlatillo!!, idRestaurante!!)
        adaptadorPlatillo!!.notifyDataSetChanged()
            }

    private fun consultarPlatillos(adaptadorPlatillo: ArrayAdapter<Platillo>, idRestaurante: String) {
        val db = Firebase.firestore
        val PlatillosRefUnico = db.collection("restaurantes").document(idRestaurante).collection("platillos")
        arregloPlatillo.clear()
        adaptadorPlatillo.notifyDataSetChanged()
        PlatillosRefUnico.orderBy("nombre")
            .get()
            .addOnSuccessListener { // it -> eso (lo que llegue)
                for (platillo in it){
                    platillo.id
                    anadirAArregloFruta(platillo)
                }
                adaptadorPlatillo.notifyDataSetChanged()
            }
            .addOnFailureListener {
                //Errores
            }
    }

    private fun anadirAArregloFruta(platillo: QueryDocumentSnapshot?) {
        Log.d("Firestore", "ID: ${platillo?.id}")
        val nombre = platillo?.getString("nombre")
        val descripcion = platillo?.getString("descripcion")
        val precio = platillo?.getDouble("precio")

        Log.d("Firestore", "Nombre: $nombre, Descripcion: $descripcion, Precio: $precio")

        val nuevoPlatillo = Platillo(
            id = platillo?.id,
            nombre = nombre ?: "",
            descripcion = descripcion ?: "",
            precio = precio ?: 0.0,
            restauranteId = null // Proporciona un valor adecuado para restauranteId si es necesario
        )
        arregloPlatillo.add(nuevoPlatillo)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        // llenar las opciones del menu
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_context_platillo, menu)

        // obtener el id del ArrayList seleccionado
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        idItemSeleccionadoPlatillo = id
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        idPlatillo = arregloPlatillo.get(idItemSeleccionadoPlatillo).id
        nombrePlatillo = arregloPlatillo.get(idItemSeleccionadoPlatillo).nombre
        return when (item.itemId) {
            R.id.menu_editar_platillo -> {
                irActividadDesdePlatillo(ActualizacionPlatillo::class.java)
                return true

            }
            R.id.menu_eliminar_platillo -> {
               eliminarPlatillo(idRestaurante, idPlatillo)
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    private fun eliminarPlatillo(idRestaurante: String?, idPlatillo: String?) {
        var db = Firebase.firestore
        var platilloReferencia =
            db.collection("restaurantes").document(idRestaurante!!).
        collection("platillos").document(idPlatillo!!)

        platilloReferencia.delete()
        consultarPlatillos(adaptadorPlatillo!!,idRestaurante!!)
    }

    private fun irActividadDesdePlatillo(
            clase: Class<*>
        ) {
            val intent = Intent(this, clase)
            intent.putExtra("idPlatillo", idPlatillo)
            intent.putExtra("idRestaurante", idRestaurante)
            intent.putExtra("nombrePlatillo", nombrePlatillo)
            startActivity(intent)
    }

    fun irAaniadirPlatillo(
        clase: Class<*>
    ) {
        val intent = Intent(this, clase)
        intent.putExtra("nombreRestaurante",  nombreRestaurante)
        intent.putExtra("idRestaurante", idRestaurante)
        startActivity(intent)
    }
}