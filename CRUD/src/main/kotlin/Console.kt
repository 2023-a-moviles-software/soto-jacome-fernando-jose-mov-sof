import com.google.gson.Gson
import java.util.*

class Console(private val crud: Crud) {
    val gson = Gson()
    val scanner = Scanner(System.`in`)


    // Menú principal
    fun ejecutarMenuPrincipal() {
        var opcion: Int
        do {
            println("---------------OPCIONES---------------")
            println("Seleccione el menú de opciones")
            println("1. Menú de restaurante")
            println("2. Menú de platillo")
            println("0. Salir")


            opcion = scanner.nextInt()
            scanner.nextLine()

            when (opcion) {
                1 -> mostrarOpcionesRestaurante()
                2 -> mostrarOpcionesPlatillo()
                0 -> break

                else -> println("Opción inválida")
            }
            println()
        } while (opcion != 0)
    }




    // Mostrar menus de restaurante y platillo
    private fun mostrarOpcionesRestaurante() {
        var opcion: Int
        do {
            println("---------------RESTAURANTES---------------")
            println("1. (C) Crear restaurante")
            println("2. (R) Listar restaurantes")
            println("3. (U) Actualizar restaurante")
            println("4. (D) Eliminar restaurante")
            println("0. Regrear menú principal")
            println("-------------------")
            print("Ingrese una opción: ")

            opcion = scanner.nextInt()
            scanner.nextLine()

            when (opcion) {
                1 -> crud.crearRestaurante()
                2 -> crud.listarRestaurantes()
                3 -> crud.actualizarRestaurante()
                4 -> crud.eliminarRestaurante()
                0 -> ejecutarMenuPrincipal()

                else -> println("Opción inválida")
            }
            println()
        } while (opcion != 0)
    }

    private fun mostrarOpcionesPlatillo() {
        var opcion: Int
        do {
            println("---------------PLATILLOS---------------")
            println("1. (C) Crear platillo para un restaurante")
            println("2. (R) Mostrar platillos")
            println("3. (U) Actualizar platillo")
            println("4. (D) Eliminar platillo")
            println("0. Regresar menú principal")
            println("-------------------")
            print("Ingrese una opción: ")

            opcion = scanner.nextInt()
            scanner.nextLine()

            when (opcion) {
                1 -> crud.crearPlatilloARestaurante()
                2 -> crud.listarPlatillos()
                3 -> crud.actualizarPlatillo()
                4 -> crud.eliminarPlatillo()
                0 -> ejecutarMenuPrincipal()
                else -> println("Opción inválida")
            }
            println()
        } while (opcion != 0)
    }




    // OBTENER TEXTO, ENTEROS Y DOUBLES
    fun obtenerTexto(mensaje: String): String {
        print(mensaje)
        return readLine() ?: ""
    }

    fun obtenerInt(mensaje: String): Int {
        while (true) {
            print(mensaje)
            val input = readLine()
            try {
                return input?.toInt() ?: 0
            } catch (e: NumberFormatException) {
                println("Entrada inválida. Intente nuevamente")
            }
        }
    }

    fun obtenerDouble(mensaje: String): Double {
        while (true) {
            print(mensaje)
            val input = readLine()
            try {
                return input?.toDouble() ?: 0.0
            } catch (e: NumberFormatException) {
                println("Entrada inválida. Intente nuevamente.")
            }
        }
    }


}