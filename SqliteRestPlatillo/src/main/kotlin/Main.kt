
import Model.SQLCrud
import View.Consola
import java.sql.DriverManager

fun main() {
    val dbUrl = "jdbc:sqlite:restaurante.db"
    val dbConnection = DriverManager.getConnection(dbUrl)

    val crud = SQLCrud(dbConnection)
    crud.crearTablas()

    val console = Consola()
    console.ejectuarConsola(crud)

    dbConnection.close()


}