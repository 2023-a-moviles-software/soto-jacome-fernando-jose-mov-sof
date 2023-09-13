package com.example.examen1bmoviles.model

class Platillo(
    var id: String?,
    var nombre: String?,
    var descripcion: String?,
    var precio: Double?,
    var restauranteId: Int?
) {


    init {
        this.nombre; this.descripcion; this.precio
    }

    // Constructor vacío necesario para Firestore
    constructor(
        id: String,
        nombre: String,
        descripcion: String,
        precio: Double,
        restauranteId: Int
    ) : this(
        id = null, // Aquí debes proporcionar un valor por defecto para 'id'
        nombre = nombre,
        descripcion = descripcion,
        precio = precio,
        restauranteId = restauranteId
    ) {
        // Puedes agregar código adicional aquí si es necesario
    }

    override fun toString(): String {
        return "\nNombre: ${nombre} " +
                "\nDescripcion: ${descripcion} " +
                "\nPrecio: ${precio} "
    }
}