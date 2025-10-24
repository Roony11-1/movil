package com.patitofeliz.trabajo2movil.config

import com.patitofeliz.trabajo2movil.service.UbicacionService
import com.patitofeliz.trabajo2movil.service.UsuarioService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
object RetrofitClient
{
    private const val BASE_URL = "http://192.168.1.5:8001/api/"

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val usuarioService: UsuarioService by lazy {
        retrofit.create(UsuarioService::class.java)
    }

    val ubicacionService: UbicacionService by lazy {
        retrofit.create(UbicacionService::class.java)
    }
}