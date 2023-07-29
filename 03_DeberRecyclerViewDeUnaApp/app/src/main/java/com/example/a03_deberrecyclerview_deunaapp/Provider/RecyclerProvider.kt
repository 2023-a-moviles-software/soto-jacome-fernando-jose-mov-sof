package com.example.a03_deberrecyclerview_deunaapp.Provider

import com.example.a03_deberrecyclerview_deunaapp.R
import com.example.a03_deberrecyclerview_deunaapp.Entity.Cuenta
import com.example.a03_deberrecyclerview_deunaapp.Entity.Promocion

class RecyclerProvider {
    companion object {
        // LIST CUENTAS
        val listCuentas = arrayListOf<Cuenta>(
            Cuenta(R.drawable.pichincha, "PRINCIPAL", "2209893029", "400.50", R.drawable.icon_star_full),
            Cuenta(R.drawable.pichincha, "ALTERNA", "3301092134", "1000.25", R.drawable.icon_star_empty),
            Cuenta(R.drawable.pichincha, "AHORROS", "5509098765", "800.30", R.drawable.icon_star_empty),
            Cuenta(R.drawable.pichincha, "GASTOS", "5509098765", "70.30", R.drawable.icon_star_empty)
        )

        // LIST PROMOCIONES
        val listPromociones = arrayListOf<Promocion>(
            Promocion(
                "https://deuna.app/assets/images/background.jpg",
                "Invita a tus amigos",
                "¡Aprovecha esta emocionante promoción! Invita a tus amigos a descargar y" +
                        "usar nuestra increíble aplicación y recibe 3 dólares totalmente GRATIS para" +
                        " disfrutar como prefieras. Comparte la experiencia y diviértete juntos" +
                        " mientras obtienes recompensas."
            ),
            Promocion(
                "https://www.pichincha.com/portal/Portals/0/landing-deuna/mujer-mujer-celular.png?ver=4GSJsine_6WMZONGlLW3QQ%3D%3D&timestamp=1675120303075",
                "Descuento Especial",
                "¡Agradecemos tu lealtad! Ahora, por cada compra que realices en " +
                        "nuestra app, recibirás un Descuento Especial del 15% en tu próxima " +
                        "compra. Comparte esta promoción con tus amigos y familiares para que " +
                        "ellos también puedan disfrutar de este increíble beneficio."
            ),
            Promocion(
                "https://www.pichincha.com/portal/Portals/0/Images/og-de-una-2.jpg?ver=2021-04-01-104545-440",
                "Tarjeta de Regalo",
                "¡Bienvenido a nuestra app! Para celebrar tu llegada, te obsequiamos " +
                        "una Tarjeta de Regalo de 10$ en tu primer registro y compra. Además, " +
                        "si invitas a tus amigos a unirse, ellos también recibirán una Tarjeta de" +
                        " Regalo de bienvenida."
            )
        )
    }
}