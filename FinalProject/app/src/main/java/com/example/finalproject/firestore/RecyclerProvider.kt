package com.example.finalproject.firestore

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.finalproject.entity.Actividad
import com.example.finalproject.entity.Amigo
import com.example.finalproject.entity.Logro
import com.example.finalproject.entity.Recordatorio
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RecyclerProvider {


    val listActividadLiveData = MutableLiveData<List<Actividad>>()
    val listRecordatorioLiveData = MutableLiveData<List<Recordatorio>>()
    val listLogrosLiveData = MutableLiveData<List<Logro>>()
    val listAmigosLiveData = MutableLiveData<List<Amigo>>()


    var listActividad = arrayListOf<Actividad>()
    var listRecordatorio = arrayListOf<Recordatorio>()
    var listLogros = arrayListOf<Logro>()
    var listAmigos = arrayListOf<Amigo>()


    // 1. ---------------------------------------------------------------------------ACTIVIDADES
    fun cargarActividades() {

        val db = Firebase.firestore

        val actividadesRefUnico = db.collection("actividades")

        actividadesRefUnico.get().addOnSuccessListener {
            for (actividad in it) {
                actividad.id
                val imagenActividad = actividad?.getString("imagenActividad") ?: ""
                val nombreActividad = actividad?.getString("nombreActividad") ?: ""
                val dificultad = actividad?.getString("dificultad") ?: ""

                val calorias = actividad?.getString("calorias")?.toInt() ?: 0
                val calificacion = actividad?.getString("calificacion")?.toDouble() ?: 0.0

                val actividadNueva =
                    Actividad(imagenActividad, nombreActividad, dificultad, calorias, calificacion)
                listActividad.add(actividadNueva)
            }
            listActividadLiveData.postValue(listActividad)
        }
            .addOnFailureListener {

            }
    }
    // -------------------------------------------------------------------------2. RECORDATORIO
    fun cargarRecordatorio() {


        val db = Firebase.firestore

        val recordatorioRefUnico = db.collection("recordatorios")

        recordatorioRefUnico.get().addOnSuccessListener {
            for (recordatorio in it) {
                recordatorio.id
                val nombreRecordatorio = recordatorio?.getString("nombreRecordario") ?: ""
                val descripcionRecordatorio =
                    recordatorio?.getString("descripcionRecordatorio") ?: ""
                val fechaRecordatorio = recordatorio?.getString("fechaRecordatorio") ?: ""
                val horaRecordatorio = recordatorio?.getString("horaRecordatorio") ?: ""
                val estaHecho = recordatorio?.getBoolean("estaHecho") ?: false

                val recordatorioNuevo = Recordatorio(
                    nombreRecordatorio,
                    descripcionRecordatorio,
                    fechaRecordatorio,
                    horaRecordatorio,
                    estaHecho
                )
                listRecordatorio.add(recordatorioNuevo)

            }
            listRecordatorioLiveData.postValue(listRecordatorio)
        }
            .addOnFailureListener {

            }
    }


    // 3---------------------------------------------------------------------------- LOGROS
    fun cargarLogros() {
        // Evita cargar los logros más de una vez
        Log.d("", "Entré a cargar logros")

        val db = Firebase.firestore
        val logrosRef = db.collection("logros")

        logrosRef.get()
            .addOnSuccessListener {
                for (logro in it) {
                    logro.id
                    val nombreLogro = logro?.getString("nombreLogro") ?: ""
                    val userNombreLogro = logro?.getString("userNombreLogro") ?: ""
                    val userImagenLogro = logro?.getString("userImagenLogro") ?: ""
                    val horaLogro = logro?.getString("horaLogro") ?: ""

                    val logroNuevo = Logro(nombreLogro, userNombreLogro, userImagenLogro, horaLogro)

                    listLogros.add(logroNuevo)

                }
                listLogrosLiveData.postValue(listLogros)
            }
            .addOnFailureListener { exception ->
            }
    }

    // 4 ------------------------------------------------------------------------------ FRIENDSHIP
    fun cargarAmigos() {
        val db = Firebase.firestore
        val amigosRef = db.collection("amigos")
        amigosRef.get()
            .addOnSuccessListener { snapshot ->
                for (amigo in snapshot) {
                    amigo.id
                    val urlImgAmigo = amigo.getString("urlImgAmigo") ?: ""
                    val nombreAmigo = amigo.getString("nombreAmigo") ?: ""
                    val experienciaAmigo = amigo.getString("experienciaAmigo") ?: ""

                    val amigoNuevo = Amigo(urlImgAmigo, nombreAmigo, experienciaAmigo)
                    listAmigos.add(amigoNuevo)
                }

                // Actualiza la lista de amigos
                listAmigosLiveData.postValue(listAmigos)
            }
            .addOnFailureListener { exception ->
                // Maneja los errores en caso de fallo
            }
    }


}