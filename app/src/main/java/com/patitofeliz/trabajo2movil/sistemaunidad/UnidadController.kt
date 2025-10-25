package com.patitofeliz.trabajo2movil.sistemaunidad

import com.patitofeliz.trabajo2movil.model.unidades.Unidad
import kotlin.collections.mutableListOf

object UnidadController
{
    private val personajes = mutableListOf<Unidad>()

    fun agregarPersonaje(unidad: Unidad)
    {
        this.personajes.add(unidad)
    }

    fun limpiarLista()
    {
        this.personajes.clear()
    }

    fun setPersonajes(nuevosPersonajes: MutableList<Unidad>)
    {
        this.limpiarLista()
        this.personajes.addAll(nuevosPersonajes)
    }

    fun obtenerPersonajes(): List<Unidad> = this.personajes
}