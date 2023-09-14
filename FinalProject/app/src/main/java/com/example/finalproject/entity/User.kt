package com.example.finalproject.entity

class Usuario(
    val nombreUsuario: String,
    val username: String,
    val fers: String,
    val fings: String,
    val peso: String,
    val estatura: String,
    val streak: String,
    val exp: String,
    val imgUsario: String
) {
    override fun toString(): String {
        return "Usuario(nombreUsuario='$nombreUsuario', username='$username', fers='$fers', fings='$fings', peso='$peso', estatura='$estatura', streak='$streak', exp='$exp', imgUsuario='$imgUsario')"
    }
}