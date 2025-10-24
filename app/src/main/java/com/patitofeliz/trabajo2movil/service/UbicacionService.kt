package com.patitofeliz.trabajo2movil.service

import com.patitofeliz.trabajo2movil.model.ubicacion.Comuna
import com.patitofeliz.trabajo2movil.model.ubicacion.Region
import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.Path

interface UbicacionService
{
    @GET("ubicacion/regiones")
    fun getRegiones(): Call<List<Region>>

    @GET("ubicacion/findbynombre/{nombre}")
    fun findByNombreComuna(@Path("nombre") nombre: String): Call<Comuna>
}