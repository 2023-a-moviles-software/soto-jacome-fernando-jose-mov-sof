import com.google.gson.Gson
import java.io.File

class Crud(private val gson: Gson) {
    private val restaurantes: MutableList<Restaurante> = mutableListOf()
    private val console: Console = Console(this)

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
    // UPDATE RESTAURANTE
    fun actualizarRestaurante() {

        listarRestaurantes()
        val idRestaurante = console.obtenerInt("Ingrese el ID del restaurante a actualizar: ")
        val restaurante = obtenerRestaurantePorId(idRestaurante)

        if (restaurante != null) {
            println("Ingrese los nuevos datos del restaurante:")
            val nuevoNombre = console.obtenerTexto("Nuevo nombre del restaurante: ")
            val nuevaDireccion = console.obtenerTexto("Nueva dirección del restaurante: ")
            val nuevaCiudad = console.obtenerTexto("Nueva ciudad del restaurante: ")
            val nuevaMichelin = console.obtenerInt("Nueva cantidad de estrellas Michelin: ")
            val nuevoPais = console.obtenerTexto("Nuevo país del restaurante: ")

            restaurante.nombre = nuevoNombre
            restaurante.direccion = nuevaDireccion
            restaurante.ciudad = nuevaCiudad
            restaurante.michelin = nuevaMichelin
            restaurante.pais = nuevoPais

            guardarDatos()

            println("Restaurante actualizado exitosamente.")
        } else {
            println("Restaurante no encontrado.")
        }
    }


    // DELETE RESTAURANTE
    fun eliminarRestaurante() {
        val maximoIdRestaurante: Int = restaurantes.maxOfOrNull { it.id } ?: 0

        while (true) {
            val id = console.obtenerInt("Ingrese el ID del restaurante a eliminar: ")

            if (id in 1..maximoIdRestaurante) {
                eliminarRestauranteById(id)
                guardarDatos()
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
        val platillos = obtenerListaPlatillos()
        for (platillo in platillos) {
            println(platillo)
        }
    }

    // UPDATE PLATILLO
    fun actualizarPlatillo() {
        listarRestaurantes()
        val idRestaurante =
            console.obtenerInt("\nIngrese el ID del restaurante donde se encuentra el platillo a actualizar: ")
        val restaurante = obtenerRestaurantePorId(idRestaurante)

        if (restaurante != null) {
            println("Los platillos disponibles para actualizar son los siguientes:")

            for (platillo in restaurante.platillos) {
                println(platillo)

            }


            if (restaurante.platillos.isNotEmpty()) {

                val idPlatillo = console.obtenerInt("\nIngrese el ID del platillo a actualizar: ")
                val platillo = obtenerPlatilloPorId(idPlatillo)

                if (platillo != null) {
                    println("Ingrese los nuevos datos del platillo:")
                    val nuevoNombre = console.obtenerTexto("Nuevo nombre del platillo: ")
                    val nuevaDescripcion = console.obtenerTexto("Nueva descripción del platillo: ")
                    val nuevoPrecio = console.obtenerDouble("Nuevo precio del platillo: ")

                    platillo.nombre = nuevoNombre
                    platillo.descripcion = nuevaDescripcion
                    platillo.precio = nuevoPrecio

                    guardarDatos()

                    println("Platillo actualizado exitosamente.")
                } else {
                    println("Platillo no encontrado.")
                }
            } else {
                println("No existen platillos en ese restaurante")
            }
        } else {
            println("Restaurante no encontrado.")
        }
    }


    // DELETE PLATILLO
    fun eliminarPlatillo() {
        val idRestaurante =
            console.obtenerInt("Ingrese el ID del restaurante donde se encuentra el platillo a eliminar: ")
        val restaurante: Restaurante? = obtenerRestaurantePorId(idRestaurante)

        println("Los platillos disponibles para eliminar son los siguientes:")
        println(restaurante?.platillos)

        val idPlatillo = console.obtenerInt("Ingrese el ID del platillo a eliminar: ")
        val platillo: Platillo? = obtenerPlatilloPorId(idPlatillo)

        restaurante?.platillos?.remove(platillo)

        guardarDatos()
    }


    // FUNCIONES PRIVADAS
    private fun generarNuevoIdRestaurante(): Int {
        return if (restaurantes.isEmpty()) 1 else restaurantes.maxByOrNull { it.id }!!.id + 1
    }

    private fun generarNuevoIdPlatillo(): Int {
        val platillos = obtenerListaPlatillos()
        return if (platillos.isEmpty()) 1 else platillos.maxByOrNull { it.id }!!.id + 1
    }


    fun obtenerListaPlatillos(): MutableList<Platillo> {
        val platillos = mutableListOf<Platillo>()
        if (restaurantes.isNotEmpty()) {
            for (restaurante in restaurantes) {
                for (platillo in restaurante.platillos) {
                    platillos.add(platillo)
                }
            }
        } else {
            println("No existen restaurantes registrados")
        }
        if (platillos.isEmpty()) println("No existen platillos registrados")
        return platillos
    }


    private fun guardarDatos() {
        val json = gson.toJson(restaurantes)
        val file = File("restaurantes.json")
        file.writeText(json)
    }


    private fun obtenerRestaurantePorId(id: Int): Restaurante? {
        return restaurantes.find { it.id == id }
    }

    private fun obtenerPlatilloPorId(id: Int): Platillo? {
        val platillos = obtenerListaPlatillos()
        return platillos.find { it.id == id }
    }

    fun eliminarRestauranteById(id: Int) {
        val restaurante: Restaurante? = obtenerRestaurantePorId(id)
        restaurantes.remove(restaurante)
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

}