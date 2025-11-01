package com.patitofeliz.trabajo2movil.model.unidades.clases

import com.patitofeliz.trabajo2movil.model.unidades.clases.atributos.Estadisticas
import com.patitofeliz.trabajo2movil.R

class Jinete :
    Clase("Jinete",
        Estadisticas(20, 5, 3, 5, 6, 0, 9, 0),
        Estadisticas(60, 17, 18, 30, 15, 15, 30, 30),
        Sprites(
            imgScreen = R.drawable.jinete_screen,
            idle = R.drawable.jinete_idle
        )
    )
{

}