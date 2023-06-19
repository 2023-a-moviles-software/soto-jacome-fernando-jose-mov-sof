data class Restaurante(
    var id: Int,
    var nombre: String,
    var direccion: String,
    var ciudad: String,
    var michelin: Int,
    var pais: String,
    var platillos: MutableList<Platillo> = mutableListOf()
)