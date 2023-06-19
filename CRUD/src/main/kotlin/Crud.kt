import com.google.gson.Gson
import java.io.File

class Crud(private val gson: Gson) {
    private val restaurantes: MutableList<Restaurante> = mutableListOf()
    private val console: Console = Console(this)
    var listaPlatillos: MutableList<Platillo> = mutableListOf()

    init {
        cargarDatos()
    }


    // CREATE RESTAURANTE
    fun crearRestaurante() {
        val restaurante = Restaurante(
            id = generarNuevoIdRestaurante(),
            nombre = console.obtenerTexto("Ingrese el nombre del restaurante: "),
            direccion = console.obtenerTexto("Ingrese la dirección del restaurante: "),
            ciudad = console.obtenerTexto("Ingrese la ciudad del restaurante: "),
            michelin = console.obtenerInt("Ingrese la cantidad de estrellas Michelin: "),
            pais = console.obtenerTexto("Ingrese el país del restaurante: ")
        )
        restaurantes.add(restaurante)

        guardarDatos()

        println("Restaurante creado exitosamente.")
    }


    // READ RESTAURANTE
    fun listarRestaurantes() {
        if (restaurantes.isNotEmpty()) {
            println("Lista de restaurantes:")
            for (restaurante in restaurantes) {
                println(restaurante)
            }
        } else {
            println("No hay restaurantes registrados.")
        }
    }


    // UPDATE RESTAURANTE


    // DELETE RESTAURANTE
    fun eliminarRestaurante() {
        var maximoIdRestaurante: Int = restaurantes.maxOfOrNull { it.id } ?: 0

        while (true) {
            val id = console.obtenerInt("Ingrese el ID del restaurante a eliminar: ")

            if (id in 1..maximoIdRestaurante) {
                eliminarRestauranteById(id)
                println("Restaurante eliminado exitosamente.")
                break
            } else {
                println("Entrada inválida. Intente nuevamente.")
            }
        }
    }


    // CREATE PLATILLO

    fun crearPlatilloARestaurante() {
        val restauranteId = console.obtenerTexto("Ingrese el ID del restaurante: ").toIntOrNull()
        if (restauranteId != null) {
            agregarPlatillo(restauranteId)
        } else {
            println("ID de restaurante inválido.")
        }
    }


    private fun agregarPlatillo(restauranteId: Int) {
        val restaurante = obtenerRestaurantePorId(restauranteId)
        if (restaurante != null) {
            val platillo = Platillo(
                id = generarNuevoIdPlatillo(),
                nombre = console.obtenerTexto("Ingrese el nombre del platillo: "),
                descripcion = console.obtenerTexto("Ingrese la descripción del platillo: "),
                precio = console.obtenerDouble("Ingrese el precio del platillo: "),
                restauranteId = restauranteId
            )
            restaurante.platillos.add(platillo)
            guardarDatos()
            println("Platillo agregado exitosamente.")
        } else {
            println("Restaurante no encontrado.")
        }
    }


    // READ PLATILLO
    fun listarPlatillos() {
        println(listaPlatillos)
    }

    // UPDATE PLATILLO


    // DELETE PLATILLO


    // FUNCIONES PRIVADAS
    private fun generarNuevoIdRestaurante(): Int {
        return if (restaurantes.isEmpty()) 1 else restaurantes.maxByOrNull { it.id }!!.id + 1
    }

    private fun generarNuevoIdPlatillo(): Int {
        return if (listaPlatillos.isEmpty()) 1 else listaPlatillos.maxByOrNull { it.id }!!.id + 1
    }


    fun obtenerListaPlatillos(): MutableList<Platillo> {
        if (restaurantes.isNotEmpty()) {
            for (restaurante in restaurantes) {
                for (platillo in restaurante.platillos) {
                    listaPlatillos.add(platillo)
                }
            }
        } else {
            println("No existen restaurantes registrados")
        }
        if (listaPlatillos.isEmpty()) println("No existen platillos registrados")
        return listaPlatillos
    }

    private fun cargarDatos() {
        val file = File("restaurantes.json")
        if (file.exists()) {
            val json = file.readText()
            val restauranteArray = gson.fromJson(json, Array<Restaurante>::class.java)
            restaurantes.addAll(restauranteArray)
        }
        obtenerListaPlatillos()
    }

    private fun guardarDatos() {
        val json = gson.toJson(restaurantes)
        val file = File("restaurantes.json")
        file.writeText(json)
    }


    private fun obtenerRestaurantePorId(id: Int): Restaurante? {
        return restaurantes.find { it.id == id }
    }

    fun eliminarRestauranteById(id: Int) {
        var restaurante: Restaurante? = obtenerRestaurantePorId(id)
        restaurantes.remove(restaurante)
    }


}