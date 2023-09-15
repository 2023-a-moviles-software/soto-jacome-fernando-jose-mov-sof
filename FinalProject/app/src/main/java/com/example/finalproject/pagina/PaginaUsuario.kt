package com.example.finalproject.pagina

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.finalproject.Login
import com.example.finalproject.MainActivity
import com.example.finalproject.R
import com.example.finalproject.firestore.Provider
import com.google.android.material.imageview.ShapeableImageView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class PaginaUsuario : AppCompatActivity() {
    private val mAuth = FirebaseAuth.getInstance()
    val auth = FirebaseAuth.getInstance()
    val user = auth.currentUser
    val userId = user?.uid

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_usuario)

        val btnLogout = findViewById<Button>(R.id.btn_logout)

        btnLogout.setOnClickListener {
            mAuth.signOut()
            val intent = Intent(this, Login::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }

        // Botón Main
        findViewById<Button>(R.id.btn_home4).setOnClickListener {
            irActividad(MainActivity::class.java)
        }
        // Boton Recordatorio
        findViewById<Button>(R.id.btn_calendar4).setOnClickListener {
            irActividad(PaginaRecordatorio::class.java)
        }
        // Boton Logros
        findViewById<Button>(R.id.btn_logro4).setOnClickListener {
            irActividad(PaginaLogros::class.java)
        }


        renderUsuario()

    }

    fun renderUsuario() {
        val db = Firebase.firestore
        val usuarioReferencia = db.collection("usuarios").document(userId.toString())

        usuarioReferencia.get()
            .addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.exists()) {
                    val data = documentSnapshot.data

                    findViewById<TextView>(R.id.tv_nombre_usuario).text =
                        data?.get("nombreUsuario") as? String
                    // Establece los demás valores en sus respectivos componentes de la misma manera
                    //findViewById<ShapeableImageView>(R.id.iv_imagenUsuario).setImageResource(R.drawable.i) // Reemplaza con la lógica para cargar la imagen
                    findViewById<TextView>(R.id.tv_user_name).text =
                        data?.get("username") as? String
                    findViewById<TextView>(R.id.tv_fers).text = data?.get("fers") as? String
                    findViewById<TextView>(R.id.tv_fings).text = data?.get("fings") as? String
                    findViewById<TextView>(R.id.tv_peso).text = data?.get("peso") as? String
                    findViewById<TextView>(R.id.tv_estatura).text = data?.get("estatura") as? String
                    findViewById<TextView>(R.id.tv_streak).text = data?.get("streak") as? String
                    findViewById<TextView>(R.id.tv_exp).text = data?.get("exp") as? String
                }
            }
            .addOnFailureListener { exception ->
                // Manejar errores si es necesario
            }
    }
    fun irActividad(
        clase: Class<*>
    ) {
        val intent = Intent(this, clase)
        startActivity(intent)

    }
}




