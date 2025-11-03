package com.patitofeliz.trabajo2movil.model.unidades

import android.widget.ImageView
import android.widget.TextView
import com.patitofeliz.trabajo2movil.combate.Combate
import com.patitofeliz.trabajo2movil.model.unidades.clases.Clase
import com.patitofeliz.trabajo2movil.model.unidades.clases.atributos.Estadisticas

class Unidad(var id: Int? = null,
             var nombre: String = "NN",
             var clase: Clase? = null,
             var nivel: Nivel,
             var idUsuario: Int? = null,
             var estadisticasUnidad: Estadisticas)
{
    var hp: Int = 0
    private var estadoActual: Estado = Estado.IDLE

    init
    {
        hp = estadisticasUnidad.pv ?: 0
    }

    enum class Estado {
        IDLE, ATACANDO, DODGEANDO
    }

    fun cambiarEstado(nuevo: Estado, imageView: ImageView, objetivo: Unidad? = null)
    {
        estadoActual = nuevo

        when (estadoActual)
        {
            Estado.IDLE -> clase?.idleState(imageView)
            Estado.ATACANDO -> {
                if (objetivo != null)
                    atacarState(imageView, objetivo)
                else
                    clase?.atacarState(imageView)
            }
            Estado.DODGEANDO -> clase?.dodgeState(imageView)
        }
    }

    fun idleState(imageView: ImageView)
    {
        cambiarEstado(Estado.IDLE, imageView)
    }

    fun atacarState(
        imageView: ImageView,
        objetivo: Unidad? = null,
        onDaño: (() -> Unit)? = null
    ) {
        estadoActual = Estado.ATACANDO

        val posOriginalX = imageView.x

        clase?.atacarState(imageView) { frameIndex ->

            // Calculamos el progreso (0.0 a 1.0) hasta el frame de daño
            val totalFrames = clase?.sprites?.ataque?.size ?: 1
            val frameDmg = clase?.sprites?.frameDmg ?: 0

            if (frameIndex >= 2 && frameIndex <= frameDmg)
            {
                imageView.x = (posOriginalX + 95)
            }

            if (frameIndex == this.clase?.sprites?.frameDmg && objetivo != null)
            {
                val dmg = calcularDmg(objetivo)
                aplicarDmg(objetivo, dmg)
                println("${this.nombre} golpea a ${objetivo.nombre} por $dmg! HP restante: ${objetivo.hp}")

                onDaño?.invoke()
            }

            if (frameIndex > frameDmg)
            {
                imageView.x = (posOriginalX - 95).coerceAtLeast(posOriginalX)
            }
        }
    }

    fun dodgeState(imageView: ImageView)
    {
        cambiarEstado(Estado.DODGEANDO, imageView)
    }

    private fun calcularDmg(objetivo: Unidad): Int
    {
        return (this.estadisticasUnidad.fuerza - objetivo.estadisticasUnidad.defensa).coerceAtLeast(0)
    }

    private fun aplicarDmg(objetivo: Unidad, dmg: Int)
    {
        objetivo.hp = (objetivo.hp - dmg).coerceAtLeast(0)
    }
}

class Nivel(var nivel: Int,
            var experiencia: Int)