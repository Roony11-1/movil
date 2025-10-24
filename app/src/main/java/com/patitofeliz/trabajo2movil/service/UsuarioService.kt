package com.patitofeliz.trabajo2movil.service

import com.patitofeliz.trabajo2movil.model.LoginRequest
import com.patitofeliz.trabajo2movil.model.Response
import com.patitofeliz.trabajo2movil.model.Usuario
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.Call

interface UsuarioService
{
    @POST("usuarios/registro")
    fun registrarUsuario(@Body usuario: Usuario): Call<Response<Usuario>>
    @POST("usuarios/login")
    fun loginUsuario(@Body loginRequest: LoginRequest): Call<Response<Usuario>>
}