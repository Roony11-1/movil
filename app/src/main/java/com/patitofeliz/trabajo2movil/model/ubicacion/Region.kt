package com.patitofeliz.trabajo2movil.model.ubicacion

data class Region(
    val id: Int,
    val nombreRegion: String,
    val comunas: List<Comuna>
)