package com.patitofeliz.trabajo2movil.combate

import ClaseInfo
import ClaseRegistro
import com.patitofeliz.trabajo2movil.model.unidades.Nivel
import com.patitofeliz.trabajo2movil.model.unidades.Unidad
import com.patitofeliz.trabajo2movil.model.unidades.clases.Clase
import kotlin.collections.mutableListOf
import kotlin.random.Random
import kotlin.random.nextInt

object Combate
{
    private var combatientes = mutableListOf<Unidad>()
    // Manejara la logica de el combate
    fun agregarCombatientes(unidad: Unidad)
    {
        if (this.combatientes.size > 2)
            return

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

    fun crearCombatiente():Unidad
    {
        var clases:List<ClaseInfo> = ClaseRegistro.obtenerTodas()
        var seleccionRandom:Int = Random.nextInt(0, clases.size)
        var claseRandom: Clase = clases[seleccionRandom].crear()
        var unidadRandom:Unidad = Unidad(null,
            "Enemigo",
            claseRandom,
            Nivel(1, 0,),
            -1,
            claseRandom.estadisticasBase)

        return unidadRandom
    }
}