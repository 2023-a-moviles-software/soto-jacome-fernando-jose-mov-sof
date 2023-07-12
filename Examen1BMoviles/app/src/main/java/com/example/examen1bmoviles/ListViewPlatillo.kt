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
import android.widget.ListView
import android.widget.TextView

class ListViewPlatillo : AppCompatActivity() {

    var idItemSeleccionadoPlatillo = 0
    var restauranteId = 0
    var idItemSeleccionadoRestaurante = 0



    companion object {
        lateinit var adaptadorPlatillo: ArrayAdapter<Platillo>
        var subArregloPlatilloXRest = ArrayList<Platillo>()
        val arregloPlatillo = BaseDatosMemoria.arregloPlatillo
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view_platillos)

        // Se recupera las variables enviadas en la anterior vista
        idItemSeleccionadoRestaurante =
            intent.getIntExtra("idItemSeleccionado", idItemSeleccionadoRestaurante)
        restauranteId = intent.getIntExtra("restauranteId", restauranteId)

        // Generamos un subarreglo de platillos que cumplan con el restaurante seleccionado
        subArregloPlatilloXRest = arregloPlatillo.filter { platillo ->
            platillo.restauranteId == restauranteId
        } as ArrayList<Platillo>

        // Inicializar adaptador
        val listViewPlatillo = findViewById<ListView>(R.id.lv_list_view_platillo)
        adaptadorPlatillo = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            subArregloPlatilloXRest
        )
        listViewPlatillo.adapter = adaptadorPlatillo
        adaptadorPlatillo.notifyDataSetChanged()

        // Muestra el nombre del restaurante
        mostrarNombreRestaurante()

        // Aniadir un nuevo platillo
        val botonAniadirPlatillo = findViewById<Button>(
            R.id.btn_aniadir_platillo
        )
        botonAniadirPlatillo.setOnClickListener {
            irAaniadirPlatillo(CreacionPlatillo::class.java)
        }

        registerForContextMenu(listViewPlatillo)
    }

    override fun onResume() {
        super.onResume()

        subArregloPlatilloXRest = arregloPlatillo.filter { platillo ->
            platillo.restauranteId == restauranteId
        } as ArrayList<Platillo>


        // Inicializar adaptador
        val listViewPlatillo = findViewById<ListView>(R.id.lv_list_view_platillo)
        adaptadorPlatillo = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            subArregloPlatilloXRest
        )
        listViewPlatillo.adapter = adaptadorPlatillo
        adaptadorPlatillo.notifyDataSetChanged()
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
        return when (item.itemId) {
            R.id.menu_editar_platillo -> {
                irActividadDesdeRestaurante(ActualizacionPlatillo::class.java)
                return true

            }

            R.id.menu_eliminar_platillo -> {
                eliminarPlatillo(adaptadorPlatillo, idItemSeleccionadoPlatillo)
                return true
            }

            else -> super.onContextItemSelected(item)
        }
    }

    private fun irActividadDesdeRestaurante(
            clase: Class<*>
        ) {
            val intent = Intent(this, clase)
            intent.putExtra("idItemSeleccionadoPlatillo", idItemSeleccionadoPlatillo)
            startActivity(intent)

    }

    fun irAaniadirPlatillo(
        clase: Class<*>
    ) {
        val intent = Intent(this, clase)
        intent.putExtra("idItemSeleccionado", idItemSeleccionadoRestaurante)
        intent.putExtra("restauranteId", restauranteId)
        startActivity(intent)

    }

    fun mostrarNombreRestaurante() {
        val textViewSeleccionadoPlatillo = findViewById<TextView>(R.id.txt_nombre_res_platillo)
        textViewSeleccionadoPlatillo.text =
            BaseDatosMemoria.arregloRestaurante[idItemSeleccionadoRestaurante].nombre
    }

    fun eliminarPlatillo(
        adaptador: ArrayAdapter<Platillo>,
        idEliminado: Int
    ) {
        var  viejoPlatillo = subArregloPlatilloXRest[idEliminado]

        subArregloPlatilloXRest.removeAt(idEliminado)

        // Modificar el arreglo general de platillos
        val index = BaseDatosMemoria.arregloPlatillo.indexOfFirst { it.id == viejoPlatillo.id }
        val platilloEliminar = BaseDatosMemoria.arregloPlatillo[index]
        BaseDatosMemoria.arregloPlatillo.remove(platilloEliminar)

        //arregloPlatillo.removeAt(idEliminado)
        adaptador.notifyDataSetChanged()

    }
}