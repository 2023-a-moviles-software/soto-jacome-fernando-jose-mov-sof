package com.example.finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth


class Login : AppCompatActivity() {
    val mAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val correo = findViewById<EditText>(R.id.tv_correo)
        val password = findViewById<EditText>(R.id.tv_password)
        val botonLogin = findViewById<Button>(R.id.btn_login)

        botonLogin.setOnClickListener {
            val correoUser = correo.text.toString().trim()
            val passwordUser = password.text.toString().trim()

            if (correoUser.isEmpty() || passwordUser.isEmpty()) {
                Toast.makeText(this, "Ingresar datos", Toast.LENGTH_SHORT).show()
            } else {
                loginUser(correoUser, passwordUser)
            }
        }
    }

    private fun loginUser(correoUser: String, passwordUser: String) {
        mAuth.signInWithEmailAndPassword(correoUser, passwordUser)
            .addOnSuccessListener { authResult ->

                val user = authResult.user
                finish()
               // val intent = Intent(this, MainActivity::class.java)
                //intent.putExtra("userId", user.)
                startActivity(Intent(this@Login, MainActivity::class.java))
            }
            .addOnFailureListener { e ->
                val errorMessage = e.message
            }
    }
}