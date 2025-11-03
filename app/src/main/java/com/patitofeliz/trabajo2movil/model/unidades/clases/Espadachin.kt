package com.patitofeliz.trabajo2movil.model.unidades.clases

import android.widget.ImageView
import com.patitofeliz.trabajo2movil.model.unidades.clases.atributos.Estadisticas
import com.patitofeliz.trabajo2movil.R

class Espadachin : Clase(
    "Espadachin",
    Estadisticas(30, 12, 14, 18, 10, 8, 10, 10),
    Estadisticas(60, 24, 29, 30, 22, 23, 30, 30),
    Sprites(
        imgScreen = R.drawable.espadachin_screen,
        idle = R.drawable.espadachin_idle,
        ataque = listOf(
            R.drawable.espadachin_at1,
            R.drawable.espadachin_at2,
            R.drawable.espadachin_at3,
            R.drawable.espadachin_at4,
            R.drawable.espadachin_at5,
            R.drawable.espadachin_at6,
            R.drawable.espadachin_at7,
            R.drawable.espadachin_at8,
            R.drawable.espadachin_at9,
            R.drawable.espadachin_at10,
            R.drawable.espadachin_at11,
            R.drawable.espadachin_at12,
            R.drawable.espadachin_at13,
            R.drawable.espadachin_at14,
            R.drawable.espadachin_at15,
            R.drawable.espadachin_at16,
            R.drawable.espadachin_at17,
            R.drawable.espadachin_at18,
            R.drawable.espadachin_at19,
            R.drawable.espadachin_at20,
            R.drawable.espadachin_at21,
            R.drawable.espadachin_at22,
            R.drawable.espadachin_at23,
            R.drawable.espadachin_at24,
            R.drawable.espadachin_at25,
            R.drawable.espadachin_at26
        ),
        delaysAtaque = List(27) { 150L }, // 27 frames, 100 ms cada uno
        frameDmg = 12,
        dodge = listOf(
            R.drawable.espadachin_dodge,
            R.drawable.espadachin_dodge
        ),
        delaysDodge = listOf(
            500
        )
    )
) {

}