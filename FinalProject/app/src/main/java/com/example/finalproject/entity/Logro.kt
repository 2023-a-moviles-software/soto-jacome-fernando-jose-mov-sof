package com.example.finalproject.entity

import java.io.StringBufferInputStream

class Logro (
    val nombreLogro : String,
    val userNombreLogro : String,
    val userImagenLogro : String,
    val horaLogro : String
) {
    override fun toString(): String {
        return "Logro(nombreLogro='$nombreLogro', userNombreLogro='$userNombreLogro', userImagenLogro='$userImagenLogro', horaLogro='$horaLogro')"
    }
}