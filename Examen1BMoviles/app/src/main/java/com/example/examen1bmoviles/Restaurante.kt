package com.example.examen1bmoviles

class Restaurante (
    var id: Int,
    var nombre: String,
    var direccion: String,
    var ciudad: String,
    var michelin: Int,
    var platillos: MutableList<Platillo> = mutableListOf()
) {

    override fun toString(): String {
        return "id: ${id} " +
                "\nnombre: ${nombre} " +
                "\ndireccion: ${direccion} " +
                "\nciudad: ${ciudad} " +
                "\nmichelin: ${michelin}" +
                "\nplatillos: ${platillos}"
    }
}