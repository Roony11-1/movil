package com.patitofeliz.trabajo2movil.model.unidades.clases

import android.widget.ImageView
import com.patitofeliz.trabajo2movil.model.unidades.clases.atributos.Estadisticas

abstract class Clase(var nombreClase: String,
                     var estadisticasBase: Estadisticas,
                     var estadisticasMaximas: Estadisticas,
                     var sprites: Sprites)
{
    fun idleState(imageView: ImageView) = sprites.idle(imageView)
    open fun atacarState(
        imageView: ImageView,
        onFrame: ((frameIndex: Int) -> Unit)? = null)
    {
        sprites.atacar(imageView, onFrame = onFrame)
        {
            idleState(imageView)
        }
    }
    open fun dodgeState(imageView: ImageView) = sprites.dodge(imageView)
}