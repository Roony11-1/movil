package com.patitofeliz.trabajo2movil.model.unidades.clases

import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import com.patitofeliz.trabajo2movil.model.unidades.clases.atributos.Estadisticas

abstract class Clase(var nombreClase: String,
                     var estadisticasBase: Estadisticas,
                     var estadisticasMaximas: Estadisticas,
                     var sprites: Sprites)
{
    fun idleState(imageView: ImageView) = sprites.idle(imageView)
    open fun atacarState(imageView: ImageView) = sprites.atacar(imageView)
    open fun dodgeState(imageView: ImageView) = sprites.dodge(imageView)
}