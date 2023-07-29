package com.example.examen1bmoviles

class BaseDatosMemoria {
    companion object {

        val arregloRestaurante = arrayListOf<Restaurante>()
        val arregloPlatillo = arrayListOf<Platillo>()

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

            // PLATILLOOS

            arregloPlatillo.add(
                Platillo(1, "Vegetariano", "Todos los vegetales en un plato", 1.00, 1)
            )
            arregloPlatillo.add(
                Platillo(2, "Carne salteada", "Carne con salsa de verdugas", 30.00, 2)
            )
            arregloPlatillo.add(
                Platillo(3, "Pollo frito", "Pollo fresco", 10.00, 2)
            )
            arregloPlatillo.add(
                Platillo(4, "Pescado sushi", "Pescado en rollo", 15.00, 3)
            )
            arregloPlatillo.add(
                Platillo(5, "Pescado frito", "Pescado con papas", 35.00, 3)
            )
            arregloPlatillo.add(
                Platillo(6, "Jamón enrollado", "Jamon de alta calidad", 5.00, 3)
            )


        }


    }
}