package com.patitofeliz.trabajo2movil.model

import com.patitofeliz.trabajo2movil.model.ubicacion.Comuna

data class Usuario(
    val nombreUsuario: String,
    val email: String,
    val contraseña: String,
    val telefono: String,
    val comuna: Comuna,
    val tipo: String,
    val profilePhoto:String
)