package com.patitofeliz.trabajo2movil.model

data class Usuario(
    val nombreUsuario: String,
    val email: String,
    val contraseña: String,
    val telefono: String,
    val region: String,
    val comuna: String,
    val tipo: String,
    val profilePhoto:String
)