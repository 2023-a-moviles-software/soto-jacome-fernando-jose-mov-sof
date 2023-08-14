
import Entity.Platillo
import Entity.Restaurante
import Model.SQLCrud
import View.Consola
import java.sql.DriverManager

/*
class RestaurantePlatilloApp(private val dbConnection: Connection) {





    fun crearTablas() {
        val statement = dbConnection.createStatement()

        statement.execute("""
            CREATE TABLE IF NOT EXISTS Restaurantes (
                id INTEGER PRIMARY KEY,
                nombre TEXT,
                direccion TEXT,
                ciudad TEXT,
                michelin INTEGER,
                pais TEXT
            )
        """)

        statement.execute("""
            CREATE TABLE IF NOT EXISTS Platillos (
                id INTEGER PRIMARY KEY,
                nombre TEXT,
                descripcion TEXT,
                precio REAL,
                restaurante_id INTEGER,
                FOREIGN KEY (restaurante_id) REFERENCES Restaurantes (id)
            )
        """)
    }


    // CREATE
    fun agregarRestaurante(restaurante: Restaurante) {
        val stmt = dbConnection.prepareStatement("INSERT INTO Restaurantes (nombre, direccion, ciudad, michelin, pais) VALUES (?, ?, ?, ?, ?)")
        stmt.setString(1, restaurante.nombre)
        stmt.setString(2, restaurante.direccion)
        stmt.setString(3, restaurante.ciudad)
        stmt.setInt(4, restaurante.michelin)
        stmt.setString(5, restaurante.pais)
        stmt.executeUpdate()
    }

    fun agregarPlatillo(platillo: Platillo) {
        val stmt = dbConnection.prepareStatement("INSERT INTO Platillos (nombre, descripcion, precio, restaurante_id) VALUES (?, ?, ?, ?)")
        stmt.setString(1, platillo.nombre)
        stmt.setString(2, platillo.descripcion)
        stmt.setDouble(3, platillo.precio)
        stmt.setInt(4, platillo.restauranteId)
        stmt.executeUpdate()
    }

    // READ
    fun obtenerRestaurantes(): List<Restaurante> {
        val stmtRestaurantes = dbConnection.createStatement()
        val resultSetRestaurantes = stmtRestaurantes.executeQuery("SELECT * FROM Restaurantes")

        val restaurantes = mutableListOf<Restaurante>()
        while (resultSetRestaurantes.next()) {
            val restaurante = Restaurante(
                id = resultSetRestaurantes.getInt("id"),
                nombre = resultSetRestaurantes.getString("nombre"),
                direccion = resultSetRestaurantes.getString("direccion"),
                ciudad = resultSetRestaurantes.getString("ciudad"),
                michelin = resultSetRestaurantes.getInt("michelin"),
                pais = resultSetRestaurantes.getString("pais")
            )

            val platillos = obtenerPlatillos(restaurante.id)
            restaurante.platillos.addAll(platillos)

            restaurantes.add(restaurante)
        }

        return restaurantes
    }

    fun obtenerPlatillos(restauranteId: Int): List<Platillo> {
        val stmt = dbConnection.prepareStatement("SELECT id, nombre, descripcion, precio FROM Platillos WHERE restaurante_id = ?")
        stmt.setInt(1, restauranteId)
        val resultSet = stmt.executeQuery()

        val platillos = mutableListOf<Platillo>()
        while (resultSet.next()) {
            platillos.add(
                Platillo(
                    resultSet.getInt("id"),
                    resultSet.getString("nombre"),
                    resultSet.getString("descripcion"),
                    resultSet.getDouble("precio"),
                    restauranteId
                )
            )
        }
        return platillos
    }

    fun actualizarRestaurante(restaurante: Restaurante) {
        val stmt = dbConnection.prepareStatement("UPDATE Restaurantes SET nombre = ?, direccion = ?, ciudad = ?, michelin = ?, pais = ? WHERE id = ?")
        stmt.setString(1, restaurante.nombre)
        stmt.setString(2, restaurante.direccion)
        stmt.setString(3, restaurante.ciudad)
        stmt.setInt(4, restaurante.michelin)
        stmt.setString(5, restaurante.pais)
        stmt.setInt(6, restaurante.id)
        stmt.executeUpdate()
    }

    fun eliminarRestaurante(restauranteId: Int) {
        val stmt = dbConnection.prepareStatement("DELETE FROM Restaurantes WHERE id = ?")
        stmt.setInt(1, restauranteId)
        stmt.executeUpdate()
    }

    fun actualizarPlatillo(platillo: Platillo) {
        val stmt = dbConnection.prepareStatement("UPDATE Platillos SET nombre = ?, descripcion = ?, precio = ? WHERE id = ?")
        stmt.setString(1, platillo.nombre)
        stmt.setString(2, platillo.descripcion)
        stmt.setDouble(3, platillo.precio)
        stmt.setInt(4, platillo.id)
        stmt.executeUpdate()
    }

    fun eliminarPlatillo(platilloId: Int) {
        val stmt = dbConnection.prepareStatement("DELETE FROM Platillos WHERE id = ?")
        stmt.setInt(1, platilloId)
        stmt.executeUpdate()
    }

    /*
    fun guardarEnJson(data: JSONObject) {
        val jsonFile = File("data.json")
        jsonFile.writeText(data.toString(4))
    }
    fun exportarDatosAJson() {
        val data = JSONObject()
        val restaurantesArray = JSONArray()

        val stmtRestaurantes = dbConnection.createStatement()
        val resultSetRestaurantes = stmtRestaurantes.executeQuery("SELECT * FROM Restaurantes")

        while (resultSetRestaurantes.next()) {
            val restaurante = Restaurante(
                id = resultSetRestaurantes.getInt("id"),
                nombre = resultSetRestaurantes.getString("nombre"),
                direccion = resultSetRestaurantes.getString("direccion"),
                ciudad = resultSetRestaurantes.getString("ciudad"),
                michelin = resultSetRestaurantes.getInt("michelin"),
                pais = resultSetRestaurantes.getString("pais")
            )

            val platillos = obtenerPlatillos(restaurante.id)
            restaurante.platillos.addAll(platillos)

            val restauranteJson = JSONObject()
            restauranteJson.put("id", restaurante.id)
            restauranteJson.put("nombre", restaurante.nombre)
            restauranteJson.put("direccion", restaurante.direccion)
            restauranteJson.put("ciudad", restaurante.ciudad)
            restauranteJson.put("michelin", restaurante.michelin)
            restauranteJson.put("pais", restaurante.pais)

            val platillosArray = JSONArray()
            for (platillo in platillos) {
                val platilloJson = JSONObject()
                platilloJson.put("id", platillo.id)
                platilloJson.put("nombre", platillo.nombre)
                platilloJson.put("descripcion", platillo.descripcion)
                platilloJson.put("precio", platillo.precio)
                platillosArray.put(platilloJson)
            }
            restauranteJson.put("platillos", platillosArray)

            restaurantesArray.put(restauranteJson)
        }

        data.put("restaurantes", restaurantesArray)
        guardarEnJson(data)
    }
    */
}
*/

fun main() {
    val dbUrl = "jdbc:sqlite:restaurante.db"
    val dbConnection = DriverManager.getConnection(dbUrl)

    val crud = SQLCrud(dbConnection)
    crud.crearTablas()

    val console = Consola()
    console.ejectuarConsola(crud)

    dbConnection.close()


}