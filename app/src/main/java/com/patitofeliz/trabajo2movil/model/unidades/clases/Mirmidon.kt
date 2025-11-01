package com.patitofeliz.trabajo2movil.model.unidades.clases

import com.patitofeliz.trabajo2movil.model.unidades.clases.atributos.Estadisticas
import com.patitofeliz.trabajo2movil.R

class Mirmidon :
    Clase("Mirmidón",
        Estadisticas(16, 4,9, 10, 2,0,8, 0),
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
            )
        )
    )
{

}