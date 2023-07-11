package com.example.examen1bmoviles

class Platillo(
    var id: Int,
    var nombre: String,
    var descripcion: String,
    var precio: Double,
    var restauranteId: Int
) {
    override fun toString(): String {
        return "id: ${id} " +
                "\nnombre: ${nombre} " +
                "\ndescripcion ${descripcion} " +
                "\nprecio ${precio} " +
                "\nrestauranteId ${restauranteId}"
    }
}