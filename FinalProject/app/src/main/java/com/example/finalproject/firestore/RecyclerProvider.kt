package com.example.finalproject.firestore

import android.util.Log
import android.widget.ArrayAdapter
import androidx.lifecycle.MutableLiveData
import com.example.finalproject.entity.Actividad
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RecyclerProvider {

    val listActividadLiveData = MutableLiveData<List<Actividad>>()

    companion object {
        var listActividad = arrayListOf<Actividad>()
    }

    fun cargarActividades(){
        consultarActividad()
    }

    fun consultarActividad() {

        val db = Firebase.firestore

        val restaurantesRefUnico = db.collection("actividades")

        restaurantesRefUnico.get().addOnSuccessListener {
            for (actividad in it) {
                actividad.id
                aniadirArregloActividad(actividad)
            }
        }
            .addOnFailureListener {

            }
    }

    private fun aniadirArregloActividad(actividad: QueryDocumentSnapshot?) {
        val imagenActividad = actividad?.getString("imagenActividad") ?: ""
        val nombreActividad = actividad?.getString("nombreActividad") ?: ""
        val dificultad = actividad?.getString("dificultad") ?: ""
        Log.d(
            "",
            "3 atributos()!!__\n__\n_____\n_____\n______\n" + imagenActividad + nombreActividad + dificultad
        )
        val calorias = actividad?.getString("calorias")?.toInt() ?: 0
        val calificacion = actividad?.getString("calificacion")?.toDouble() ?: 0.0

        val actividadNueva =
            Actividad(imagenActividad, nombreActividad, dificultad, calorias, calificacion)
        Log.d(
            "",
            "aniadirArregloActividad()!!__\n__\n_____\n_____\n______\n" + actividadNueva.toString()
        )
        listActividad.add(actividadNueva)
        listActividadLiveData.postValue(listActividad)

    }


}