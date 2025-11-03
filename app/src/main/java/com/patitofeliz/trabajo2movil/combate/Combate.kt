package com.patitofeliz.trabajo2movil.combate

import ClaseInfo
import ClaseRegistro
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.patitofeliz.trabajo2movil.model.unidades.Nivel
import com.patitofeliz.trabajo2movil.model.unidades.Unidad
import com.patitofeliz.trabajo2movil.model.unidades.clases.Clase
import kotlin.collections.mutableListOf
import kotlin.random.Random

object Combate
{
    private var combatientes = mutableListOf<Unidad>()
    // Manejara la logica de el combate
    fun agregarCombatientes(unidad: Unidad)
    {
        if (this.combatientes.size > 2)
            return

        unidad.hp = unidad.estadisticasUnidad.pv
        this.combatientes.add(unidad)
    }

    fun limpiarListaCombatientes()
    {
        combatientes.clear()
    }

    fun getAtacante1(): Unidad
    {
        return combatientes[0]
    }

    fun getAtacante2(): Unidad
    {
        return combatientes[1]
    }

    fun crearCombatiente():Unidad {
        var clases: List<ClaseInfo> = ClaseRegistro.obtenerTodas()
        var seleccionRandom: Int = Random.nextInt(0, clases.size)
        var claseRandom: Clase = clases[seleccionRandom].crear()
        var unidadRandom: Unidad = Unidad(
            null,
            "Enemigo",
            claseRandom,
            Nivel(1, 0,),
            -1,
            claseRandom.estadisticasBase
        )

        return unidadRandom
    }

    fun actualizarHP(hp1: TextView, hp2: TextView)
    {
        hp1.text = "PV: ${getAtacante1().hp}"
        hp2.text = "PV: ${getAtacante2().hp}"
    }

    fun iniciarCombate(imageView1: ImageView, imageView2: ImageView, hp1:TextView, hp2:TextView)
    {
        // Para que se miren
        imageView1.scaleX = -1f
        var unidad1 = getAtacante1().idleState(imageView1)
        var unidad2 = getAtacante2().idleState(imageView2)

        actualizarHP(hp1, hp2)
    }

    fun atacar(atacante: Unidad, objetivo: Unidad, objetivoImageView: ImageView, hp1: TextView, hp2: TextView)
    {
        atacante.atacarState(objetivoImageView, objetivo) {
            actualizarHP(hp1, hp2)
        }
    }
}