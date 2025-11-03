package com.patitofeliz.trabajo2movil.model.unidades.clases

import android.widget.ImageView
import com.patitofeliz.trabajo2movil.model.unidades.clases.atributos.Estadisticas
import com.patitofeliz.trabajo2movil.R

class Mirmidon : Clase(
    "Mirmidón",
    Estadisticas(16, 4, 9, 10, 2, 0, 8, 0),
    Estadisticas(60, 17, 18, 30, 15, 15, 30, 30),
    Sprites(
        imgScreen = R.drawable.mirmidon_screen,
        idle = R.drawable.mirmidon_idle,
        ataque = listOf(
            R.drawable.mirmidon_at1,
            R.drawable.mirmidon_at2,
            R.drawable.mirmidon_at3,
            R.drawable.mirmidon_at4,
            R.drawable.mirmidon_at5,
            R.drawable.mirmidon_at6,
            R.drawable.mirmidon_at7,
            R.drawable.mirmidon_at8,
            R.drawable.mirmidon_at9,
            R.drawable.mirmidon_at10,
            R.drawable.mirmidon_at11,
            R.drawable.mirmidon_at12
        ),
        delaysAtaque = listOf(
            131, 111, 78, 62, 99, 498, 69, 88, 113, 132, 104
        ),
        frameDmg = 6,
        dodge = listOf(
            R.drawable.mirmidon_dogde1,
            R.drawable.mirmidon_dodge2
        ),
        delaysDodge = listOf(
            1000
        )
    )
)
{

}