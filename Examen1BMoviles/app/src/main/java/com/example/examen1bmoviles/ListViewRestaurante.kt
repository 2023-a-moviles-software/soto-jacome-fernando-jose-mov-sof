package com.example.examen1bmoviles

import android.app.Activity
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
import android.widget.EditText
import android.widget.ListView
import androidx.activity.result.contract.ActivityResultContracts

class ListViewRestaurante : AppCompatActivity() {

    // Variables
    val arregloRestaurante = BaseDatosMemoria.arregloRestaurante
    var idItemSeleccionado = 0

    companion object {
        lateinit var adaptador: ArrayAdapter<Restaurante>
    }


    // On create
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view_restaurante)

        // Inicializar adaptador

        val listViewRestaurante = findViewById<ListView>(R.id.lv_list_view_restaurante)
        adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            arregloRestaurante
        )

        listViewRestaurante.adapter = adaptador
        adaptador.notifyDataSetChanged()


        // Crear un restuarante
        val botonAniadirListViewRestaurante = findViewById<Button>(R.id.btn_aniadir_list_view)
        botonAniadirListViewRestaurante.setOnClickListener {
            crearRestaurante(adaptador)
        }

        registerForContextMenu(listViewRestaurante)

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
        return when (item.itemId) {
            R.id.menu_editar_res -> {
                irActividadActualizarRestaurante(ActualizarRestaurante::class.java)
                return true
            }

            R.id.menu_eliminar_res -> {
                eliminarRestaurante(adaptador, idItemSeleccionado)
                return true
            }

            R.id.menu_platillos_res -> {
                print("platillos")
                return true
            }

            else -> super.onContextItemSelected(item)
        }
    }


    // FUNCIONES CRUD

    //CREATE
    fun crearRestaurante(
        adaptador: ArrayAdapter<Restaurante>
    ) {
        val id = findViewById<EditText>(R.id.input_id)
        val nombreRestaurante = findViewById<EditText>(R.id.input_nombre)
        val direccion = findViewById<EditText>(R.id.input_direccion)
        val ciudad = findViewById<EditText>(R.id.input_ciudad)
        val michelin = findViewById<EditText>(R.id.input_michelin)
        arregloRestaurante.add(

            Restaurante(
                id.text.toString().toInt(),
                nombreRestaurante.text.toString(),
                direccion.text.toString(),
                ciudad.text.toString(),
                michelin.text.toString().toInt()
            )
        )
        adaptador.notifyDataSetChanged()

        // Borrar el texto de los campos de entrada después de crear el restaurante
        id.text.clear()
        nombreRestaurante.text.clear()
        direccion.text.clear()
        ciudad.text.clear()
        michelin.text.clear()
    }

    // UPDATE
    // DELETE
    fun eliminarRestaurante(
        adaptador: ArrayAdapter<Restaurante>,
        idEliminado: Int
    ) {
        arregloRestaurante.removeAt(idEliminado)
        adaptador.notifyDataSetChanged()

    }

    // Privadas

    fun irActividadActualizarRestaurante(
        clase: Class<*>
    ) {
        val intent = Intent(this, clase)
        intent.putExtra("idItemSeleccionado", idItemSeleccionado)

        startActivity(intent)
    }
}