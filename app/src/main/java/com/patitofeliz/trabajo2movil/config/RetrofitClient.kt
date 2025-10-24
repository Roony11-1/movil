package com.patitofeliz.trabajo2movil.config

import com.patitofeliz.trabajo2movil.service.UsuarioService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
object RetrofitClient
{
    private const val BASE_URL = "http://192.168.1.5:8001/api/"

    val instance: UsuarioService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(UsuarioService::class.java)
    }
}