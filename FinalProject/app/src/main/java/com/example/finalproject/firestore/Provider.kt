package com.example.finalproject.firestore

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.finalproject.entity.Actividad
import com.example.finalproject.entity.Amigo
import com.example.finalproject.entity.Logro
import com.example.finalproject.entity.Recordatorio
import com.example.finalproject.entity.Usuario
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Provider {

    val auth = FirebaseAuth.getInstance()
    val user = auth.currentUser
    val userId = user?.uid

    val listActividadLiveData = MutableLiveData<List<Actividad>>()
    val listRecordatorioLiveData = MutableLiveData<List<Recordatorio>>()
    val listLogrosLiveData = MutableLiveData<List<Logro>>()
    val listAmigosLiveData = MutableLiveData<List<Amigo>>()
    val listUsuariosLiveData = MutableLiveData<List<Usuario>>()
    var usuarioActual = Usuario("","","","","","","","","")






    // 1. ---------------------------------------------------------------------------ACTIVIDADES
    fun cargarActividades() {

        val db = Firebase.firestore

        val actividadesRefUnico = db.collection("actividades").whereEqualTo("userId", userId)
        var listActividad = arrayListOf<Actividad>()

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

        val recordatorioRefUnico = db.collection("recordatorios").whereEqualTo("userId", userId)
        var listRecordatorio = arrayListOf<Recordatorio>()

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
        val logrosRef = db.collection("logros").whereEqualTo("userId", userId)
        var listLogros = arrayListOf<Logro>()

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
        val amigosRef = db.collection("amigos").whereEqualTo("userId", userId)
        var listAmigos = arrayListOf<Amigo>()

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

    // 5---------------------------------------------------------------------------------USUARIOS
    fun cargarUsuarios() {
        val db = Firebase.firestore
        val usersCollection = db.collection("usuarios")
        usersCollection.document(userId.toString())
            .get()
            .addOnSuccessListener { documentSnapshot ->


                if (documentSnapshot.exists()) {
                    val nombreUsuario = documentSnapshot.getString("nombreUsuario") ?: ""

                    val username = documentSnapshot.getString("username") ?: ""
                    val fers = documentSnapshot.getString("fers") ?: ""
                    val fings = documentSnapshot.getString("fings") ?: ""
                    val peso = documentSnapshot.getString("peso") ?: ""
                    val estatura = documentSnapshot.getString("estatura") ?: ""
                    val streak = documentSnapshot.getString("streak") ?: ""
                    val exp = documentSnapshot.getString("exp") ?: ""
                    val imgUsuario = documentSnapshot.getString("imgUsuario") ?: ""

                    usuarioActual =
                        Usuario(nombreUsuario, username, fers, fings, peso, estatura, streak, exp, imgUsuario)

                }
            }
            .addOnFailureListener { exception ->
                // Maneja los errores en caso de fallo
            }
    }

}