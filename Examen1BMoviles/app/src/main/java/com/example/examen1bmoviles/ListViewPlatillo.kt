package com.example.examen1bmoviles

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView

class ListViewPlatillo : AppCompatActivity() {

    var restauranteId = 0
    var idItemSeleccionado = 0
    val arregloPlatillo = BaseDatosMemoria.arregloPlatillo


    companion object {
        lateinit var adaptadorPlatillo: ArrayAdapter<Platillo>

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view_platillos)


        // Se recupera las variables enviadas en la anterior vista
        idItemSeleccionado = intent.getIntExtra("idItemSeleccionado", idItemSeleccionado)
        restauranteId = intent.getIntExtra("restauranteId", restauranteId)


        // Generamos un subarreglo de platillos que cumplan con el restaurante seleccionado
        var subArregloPlatilloXRest = arregloPlatillo.filter { platillo ->
            platillo.restauranteId == restauranteId
        }


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

    }

    override fun onResume() {
        super.onResume()

        var subArregloPlatilloXRest = arregloPlatillo.filter { platillo ->
            platillo.restauranteId == restauranteId
        }


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



    fun irAaniadirPlatillo(
        clase: Class<*>
    ) {
        val intent = Intent(this, clase)
        intent.putExtra("idItemSeleccionado", idItemSeleccionado)
        intent.putExtra("restauranteId", restauranteId)
        startActivity(intent)

    }

    fun mostrarNombreRestaurante() {
        val textViewSeleccionadoPlatillo = findViewById<TextView>(R.id.txt_nombre_res_platillo)
        textViewSeleccionadoPlatillo.text =
            BaseDatosMemoria.arregloRestaurante[idItemSeleccionado].nombre
    }


}