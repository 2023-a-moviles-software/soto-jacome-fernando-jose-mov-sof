package View

import Entity.Platillo
import Entity.Restaurante
import Model.SQLCrud

class Consola {
    fun ejectuarConsola(crud: SQLCrud) {
        while (true) {
            println("***  BIENVENIDO A LA GESTIÓN DE RESTAURANTES Y PLATILLOS  ***\n")
            println("-Sección para crear")
            println("1. (C) Agregar Restaurante")
            println("2. (C) Agregar Platillo")
            println("-Sección para mostrar")
            println("3. (R) Mostrar Restaurantes")
            println("4. (R) Mostrar Platillos de un Restaurante")
            println("-Sección para actualizar")
            println("5. (U) Actualizar Restaurante")
            println("6. (U) Actualizar Platillo")
            println("-Sección para eliminar")
            println("7. (D) Eliminar Restaurante")
            println("8. (D) Eliminar Platillo\n")
            println("9. Salir")

            print("Selecciona una opción: ")
            when (readLine()) {
                "1" -> {

                    val nombre = readStringInput("Ingrese el nombre del restaurante:")
                    val direccion = readStringInput("Ingrese la dirección del restaurante:")
                    val ciudad = readStringInput("Ingrese la ciudad del restaurante:")
                    val michelin = readIntInput("Ingrese la calificación Michelin del restaurante (si no tiene, ingrese 0):")
                    val pais = readStringInput("Ingrese el país del restaurante:")

                    val restaurante = Restaurante(
                        id = 0, // SQLite genera automáticamente un id único incremental
                        nombre = nombre,
                        direccion = direccion,
                        ciudad = ciudad,
                        michelin = michelin,
                        pais = pais
                    )
                    crud.agregarRestaurante(restaurante)
                    println("Restaurante agregado con éxito!")
                }

                "2" -> {
                    val platillo = Platillo(
                        id = 0, // SQLite genera automáticamente un id único incremental
                        nombre = readStringInput("Ingrese el nombre del platillo:"),
                        descripcion = readStringInput("Ingrese la descripción del platillo:"),
                        precio = readDoubleInput("Ingrese el precio del platillo:", defaultValue = 0.0),
                        restauranteId = readIntInput("Ingrese el ID del restaurante al que pertenece el platillo:", defaultValue = 0)
                    )
                    crud.agregarPlatillo(platillo)
                    println("Platillo agregado con éxito!")
                }

                "3" -> {
                    val restaurantes = crud.obtenerRestaurantes()
                    println("Restaurantes:")
                    for (restaurante in restaurantes) {
                        println(
                            "\nID: ${restaurante.id} Nombre: ${restaurante.nombre}, Ciudad: ${restaurante.ciudad}, " +
                                    "Michelin: " +
                                    "${restaurante.michelin}"
                        )
                        println("Platillos:")
                        for (platillo in restaurante.platillos) {
                            println(
                                "- (${platillo.id}) Nombre: ${platillo.nombre}, Descripción: ${platillo.descripcion}," +
                                        " Precio:" +
                                        " " +
                                        "${platillo.precio}"
                            )
                        }
                    }
                }

                "4" -> {
                    print("Ingrese el ID del restaurante para mostrar los platillos: ")
                    val restauranteId = readLine()?.toIntOrNull() ?: 0
                    if (!crud.validarID(restauranteId)) continue

                    val platillos = crud.obtenerPlatillos(restauranteId)
                    println("Platillos del restaurante: ${restauranteId}")
                    platillos.forEach { platillo ->
                        println(
                            "Id: ${platillo.id}, Nombre: ${platillo.nombre}, Descripción: ${
                                platillo
                                    .descripcion
                            }, " +
                                    "Precio: " +
                                    "${
                                        platillo
                                            .precio
                                    }"
                        )
                    }
                }

                "5" -> {

                    val idRestaurante = readIntInput("Ingrese el ID del restaurante que desea actualizar:", defaultValue = 0)
                    val nombre = readStringInput("Ingrese el nuevo nombre del restaurante:")
                    val direccion = readStringInput("Ingrese la nueva dirección del restaurante:")
                    val ciudad = readStringInput("Ingrese la nueva ciudad del restaurante:")
                    val michelin = readIntInput("Ingrese la nueva calificación Michelin del restaurante (si no tiene, ingrese 0):", defaultValue = 0)
                    val pais = readStringInput("Ingrese el nuevo país del restaurante:")


                    val restaurante = Restaurante(
                        id = idRestaurante,
                        nombre = nombre,
                        direccion = direccion,
                        ciudad = ciudad,
                        michelin = michelin,
                        pais = pais
                    )
                    crud.actualizarRestaurante(restaurante)
                    println("Restaurante actualizado con éxito!")
                }

                "6" -> {
                    val idPlatillo = readIntInput("Ingrese el ID del platillo que desea actualizar:", defaultValue = 0)
                    val nombre = readStringInput("Ingrese el nuevo nombre del platillo:")
                    val descripcion = readStringInput("Ingrese la nueva descripción del platillo:")
                    val precio = readDoubleInput("Ingrese el nuevo precio del platillo:", defaultValue = 0.0)
                    val restauranteId = readIntInput("Ingrese el nuevo ID del restaurante al que pertenece el platillo:", defaultValue = 0)

                    val platillo = Platillo(
                        id = idPlatillo,
                        nombre = nombre,
                        descripcion = descripcion,
                        precio = precio,
                        restauranteId = restauranteId
                    )
                    crud.actualizarPlatillo(platillo)
                    println("Platillo actualizado con éxito!")
                }

                "7" -> {
                    print("Ingrese el ID del restaurante a eliminar: ")
                    val restauranteId = readLine()?.toIntOrNull() ?: 0
                    if (!crud.validarID(restauranteId)) continue

                    crud.eliminarRestaurante(restauranteId)
                    println("Restaurante eliminado con éxito!")
                }

                "8" -> {
                    print("Ingrese el ID del platillo a eliminar: ")
                    val platilloId = readLine()?.toIntOrNull() ?: 0
                    if (!crud.validarID(platilloId)) continue

                    crud.eliminarPlatillo(platilloId)
                    println("Platillo eliminado con éxito!")
                }

                "9" -> {
                    break
                }

                else -> println("Opción inválida. Por favor, elija una opción válida.")
            }
        }

    }
    fun readIntInput(message: String, defaultValue: Int = 0): Int {
        println(message)
        return readLine()?.toIntOrNull() ?: defaultValue
    }

    fun readStringInput(message: String): String {
        println(message)
        return readLine() ?: ""
    }
    fun readDoubleInput(message: String, defaultValue: Double = 0.0): Double {
        println(message)
        return readLine()?.toDoubleOrNull() ?: defaultValue
    }
}