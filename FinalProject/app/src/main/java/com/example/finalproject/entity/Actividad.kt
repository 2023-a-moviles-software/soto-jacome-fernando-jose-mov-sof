package com.example.finalproject.entity

class Actividad(
    val imagenActividad: String,
    val nombreActividad: String,
    val dificultad: String,
    val calorias: Int,
    val calificacion: Double
) {
    override fun toString(): String {
        return "Actividad(imagenActividad='$imagenActividad', nombreActividad='$nombreActividad', dificultad='$dificultad', calorias=$calorias, calificacion=$calificacion)"
    }
}