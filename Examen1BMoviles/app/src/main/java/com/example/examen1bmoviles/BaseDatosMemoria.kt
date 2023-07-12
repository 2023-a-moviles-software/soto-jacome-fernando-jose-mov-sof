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
                Restaurante(9, "TabladelToro", "Francia", "Paris", 1)
            )

            // PLATILLOOS


            arregloPlatillo.add(
                Platillo(1,"Carne salteada", "Carne con salsa de verdugas", 30.00, 2)
            )
            arregloPlatillo.add(
                Platillo(2,"Pollo frito", "Pollo fresco", 10.00, 2)
            )
            arregloPlatillo.add(
                Platillo(3,"Pescado sushi", "Pescado en rollo", 15.00, 3)
            )
            arregloPlatillo.add(
                Platillo(4,"Pescado frito", "Pescado con papas", 35.00, 3)
            )
            arregloPlatillo.add(
                Platillo(5,"Jamón enrollado", "Jamon de alta calidad", 5.00, 3)
            )
            arregloPlatillo.add(
                Platillo(6,"Vegetariano", "Todos los vegetales en un plato", 1.00, 1)
            )

        }


    }
}