package com.example.examen1bmoviles.model

import android.os.Parcel
import android.os.Parcelable
import android.widget.EditText

data class Restaurante(
    var id: String?, // Cambiado a String
    var nombre: String?,
    var direccion: String?,
    var ciudad: String?,
    var michelin: Int?,
    var platillos: MutableList<Platillo> = mutableListOf()
) {
    // Constructor vacío necesario para Firestore
    constructor() : this(null, null, null, null, null)

        override fun toString(): String {
        return "Nombre: ${nombre} " +
                "\nDireccion: ${direccion} " +
                "\nCiudad: ${ciudad} " +
                "\nMichelin: ${michelin}" /*+
                "\nplatillos: ${platillos}"*/
    }


}