package com.patitofeliz.trabajo2movil.model.unidades

import android.widget.ImageView
import com.patitofeliz.trabajo2movil.model.unidades.clases.Clase
import com.patitofeliz.trabajo2movil.model.unidades.clases.atributos.Estadisticas

class Unidad(var id: Int? = null,
             var nombre: String = "NN",
             var clase: Clase? = null,
             var nivel: Nivel,
             var idUsuario: Int? = null,
             var estadisticasUnidad: Estadisticas)
{
    enum class Estado {
        IDLE, ATACANDO, DODGEANDO
    }

    private var estadoActual: Estado = Estado.IDLE

    fun cambiarEstado(nuevo: Estado, imageView: ImageView)
    {
        estadoActual = nuevo

        when (estadoActual)
        {
            Estado.IDLE -> clase?.idleState(imageView)
            Estado.ATACANDO -> clase?.atacarState(imageView)
            Estado.DODGEANDO -> clase?.dodgeState(imageView)
        }
    }

    fun idleState(imageView: ImageView)
    {
        cambiarEstado(Estado.IDLE, imageView)
    }

    fun atacarState(imageView: ImageView)
    {
        cambiarEstado(Estado.ATACANDO, imageView)
    }

    fun dodgeState(imageView: ImageView)
    {
        cambiarEstado(Estado.DODGEANDO, imageView)
    }
}

class Nivel(var nivel: Int,
            var experiencia: Int)