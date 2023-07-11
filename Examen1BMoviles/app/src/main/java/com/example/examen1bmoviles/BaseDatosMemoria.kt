package com.example.examen1bmoviles

class BaseDatosMemoria {
    companion object {
        val arregloRestaurante = arrayListOf<Restaurante>()

        init {
            arregloRestaurante.add(
                Restaurante(1, "HoneyAndHoney", "Ecuador", "Quito", 3)
            )
            arregloRestaurante.add(
                Restaurante(2, "PizzHut", "Colombia", "Medellin", 2)
            )
            arregloRestaurante.add(
                Restaurante(3, "TabladelToro", "Francia", "Paris", 1)
            )
        }

        fun eliminarRestaurante(idEliminado: Int){
            arregloRestaurante.removeAt(idEliminado)
        }
    }
}