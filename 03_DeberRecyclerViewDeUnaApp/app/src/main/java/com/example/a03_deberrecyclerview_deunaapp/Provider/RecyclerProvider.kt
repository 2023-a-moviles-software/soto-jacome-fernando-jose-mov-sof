package com.example.a03_deberrecyclerview_deunaapp.Provider

import com.example.a03_deberrecyclerview_deunaapp.R
import com.example.a03_deberrecyclerview_deunaapp.Entity.Cuenta
import com.example.a03_deberrecyclerview_deunaapp.Entity.Promocion

class RecyclerProvider {
    companion object {
        // LIST CUENTAS
        val listCuentas = arrayListOf<Cuenta>(
            Cuenta(R.drawable.pichincha, "PRINCIPAL", "2209893029", "400.50"),
            Cuenta(R.drawable.pichincha, "SECUNDARIA", "3301092134", "1000.25"),
            Cuenta(R.drawable.pichincha, "AHORROS", "5509098765", "800.30"),
            Cuenta(R.drawable.pichincha, "GASTOS", "5509098765", "70.30")
        )

        // LIST PROMOCIONES
        val listPromociones = arrayListOf<Promocion>(
            Promocion(
                "https://deuna.app/assets/img/illustrations/bg-banner-promos-desktop3.png",
                "Invita a tus amigos",
                "¡Aprovecha esta emocionante promoción! Invita a tus amigos a descargar y" +
                        "usar nuestra increíble aplicación y recibe 3 dólares totalmente GRATIS para" +
                        " disfrutar como prefieras. Comparte la experiencia y diviértete juntos" +
                        " mientras obtienes recompensas."
            ),
            Promocion(
                "https://deuna.app/assets/img/icon/promos/icon_persona.png",
                "Descuento Especial",
                "¡Agradecemos tu lealtad! Ahora, por cada compra que realices en " +
                        "nuestra app, recibirás un Descuento Especial del 15% en tu próxima " +
                        "compra. Comparte esta promoción con tus amigos y familiares para que " +
                        "ellos también puedan disfrutar de este increíble beneficio."
            ),
            Promocion(
                "https://deuna.app/assets/img/illustrations/lading_referidos_feb.png",
                "Tarjeta de Regalo",
                "¡Bienvenido a nuestra app! Para celebrar tu llegada, te obsequiamos " +
                        "una Tarjeta de Regalo de 10$ en tu primer registro y compra. Además, " +
                        "si invitas a tus amigos a unirse, ellos también recibirán una Tarjeta de" +
                        " Regalo de bienvenida."
            )
        )
    }
}