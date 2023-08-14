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
                    val restaurante = Restaurante(
                        id = 0, // SQLite genera automáticamente un id único incremental
                        nombre = readLine() ?: "",
                        direccion = readLine() ?: "",
                        ciudad = readLine() ?: "",
                        michelin = readLine()?.toIntOrNull() ?: 0,
                        pais = readLine() ?: ""
                    )
                    crud.agregarRestaurante(restaurante)
                    println("Restaurante agregado con éxito!")
                }
                "2" -> {
                    val platillo = Platillo(
                        id = 0, // SQLite genera automáticamente un id único incremental
                        nombre = readLine() ?: "",
                        descripcion = readLine() ?: "",
                        precio = readLine()?.toDoubleOrNull() ?: 0.0,
                        restauranteId = readLine()?.toIntOrNull() ?: 0
                    )
                    crud.agregarPlatillo(platillo)
                    println("Platillo agregado con éxito!")
                }
                "3" -> {
                    val restaurantes = crud.obtenerRestaurantes()
                    println("Restaurantes:")
                    for (restaurante in restaurantes) {
                        println("ID: ${restaurante.id} Nombre: ${restaurante.nombre}, Ciudad: ${restaurante.ciudad}, " +
                                "Michelin: " +
                                "${restaurante.michelin}")
                        println("Platillos:")
                        for (platillo in restaurante.platillos) {
                            println("- Nombre: ${platillo.nombre}, Descripción: ${platillo.descripcion}, Precio: " +
                                    "${platillo.precio}")
                        }
                    }
                }
                "4" -> {
                    print("Ingrese el ID del restaurante para mostrar los platillos: ")
                    val restauranteId = readLine()?.toIntOrNull() ?: 0
                    val platillos = crud.obtenerPlatillos(restauranteId)
                    println("Platillos del restaurante: ${restauranteId}")
                    platillos.forEach { platillo ->
                        println("Nombre: ${platillo.nombre}, Descripción: ${platillo.descripcion}, Precio: ${platillo
                            .precio}")
                    }
                }
                "5" -> {
                    val restaurante = Restaurante(
                        id = readLine()?.toIntOrNull() ?: 0,
                        nombre = readLine() ?: "",
                        direccion = readLine() ?: "",
                        ciudad = readLine() ?: "",
                        michelin = readLine()?.toIntOrNull() ?: 0,
                        pais = readLine() ?: ""
                    )
                    crud.actualizarRestaurante(restaurante)
                    println("Restaurante actualizado con éxito!")
                }

                "6" -> {
                    val platillo = Platillo(
                        id = readLine()?.toIntOrNull() ?: 0,
                        nombre = readLine() ?: "",
                        descripcion = readLine() ?: "",
                        precio = readLine()?.toDoubleOrNull() ?: 0.0,
                        restauranteId = readLine()?.toIntOrNull() ?: 0
                    )
                    crud.actualizarPlatillo(platillo)
                    println("Platillo actualizado con éxito!")
                }
                "7" -> {
                    print("Ingrese el ID del restaurante a eliminar: ")
                    val restauranteId = readLine()?.toIntOrNull() ?: 0
                    crud.eliminarRestaurante(restauranteId)
                    println("Restaurante eliminado con éxito!")
                }
                "8" -> {
                    print("Ingrese el ID del platillo a eliminar: ")
                    val platilloId = readLine()?.toIntOrNull() ?: 0
                    crud.eliminarPlatillo(platilloId)
                    println("Platillo eliminado con éxito!")
                }

                "9" -> {
                    /*app.exportarDatosAJson()
                    println("Datos exportados a JSON. ¡Hasta luego!")*/
                    break
                }
                else -> println("Opción inválida. Por favor, elija una opción válida.")
            }
        }
    }
}