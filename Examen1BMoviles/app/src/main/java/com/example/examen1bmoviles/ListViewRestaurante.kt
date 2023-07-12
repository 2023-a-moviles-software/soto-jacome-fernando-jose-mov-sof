package com.example.examen1bmoviles

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView

class ListViewRestaurante : AppCompatActivity() {

    // Variables
    val arregloRestaurante = BaseDatosMemoria.arregloRestaurante
    var idItemSeleccionado = 0
    var restauranteId = 0

    companion object {
        lateinit var adaptadorRestaurante: ArrayAdapter<Restaurante>
    }


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
        adaptadorRestaurante.notifyDataSetChanged()


        // Ir a crear restaurante
        val botonInternoAniadirRestaurante = findViewById<Button>(R.id.btn_aniadir_externo_restaurante)
        botonInternoAniadirRestaurante.setOnClickListener{
            irActividadDesdeRestaurante(CreacionRestaurante::class.java)
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
                irActividadDesdeRestaurante(ActualizacionRestaurante::class.java)
                return true
            }

            R.id.menu_eliminar_res -> {
                eliminarRestaurante(adaptadorRestaurante, idItemSeleccionado)
                return true
            }

            R.id.menu_platillos_res -> {
                val restaurate = arregloRestaurante[idItemSeleccionado]
                restauranteId = restaurate.id

                irActividadDesdeRestaurante(ListViewPlatillo::class.java)
                return true
            }

            else -> super.onContextItemSelected(item)
        }
    }


    // FUNCIONES CRUD

    //CREATE


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

    fun irActividadDesdeRestaurante(
        clase: Class<*>
    ) {
        val intent = Intent(this, clase)
        intent.putExtra("idItemSeleccionado", idItemSeleccionado)
        intent.putExtra("restauranteId", restauranteId)

        startActivity(intent)
    }


}