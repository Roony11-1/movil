package com.patitofeliz.trabajo2movil.model.unidades.clases

import com.patitofeliz.trabajo2movil.model.unidades.clases.atributos.Estadisticas
import com.patitofeliz.trabajo2movil.R

class Jinete :
    Clase("Jinete",
        Estadisticas(20, 5, 3, 5, 6, 0, 9, 0),
        Estadisticas(60, 17, 18, 30, 15, 15, 30, 30),
        Sprites(
            imgScreen = R.drawable.jinete_screen,
            idle = R.drawable.jinete_idle,
            ataque = listOf(
                R.drawable.jinete_at1,
                R.drawable.jinete_at2,
                R.drawable.jinete_at3,
                R.drawable.jinete_at4,
                R.drawable.jinete_at5,
                R.drawable.jinete_at6,
                R.drawable.jinete_at7,
                R.drawable.jinete_at8,
                R.drawable.jinete_at9,
                R.drawable.jinete_at10,
                R.drawable.jinete_at11,
                R.drawable.jinete_at12,
                R.drawable.jinete_at13
            ),
            delaysAtaque = listOf(250, 175, 200, 125, 100, 200, 200, 200, 300, 250, 250, 200)
        )
    )
{

}