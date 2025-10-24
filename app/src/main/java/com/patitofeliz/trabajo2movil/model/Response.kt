package com.patitofeliz.trabajo2movil.model

data class Response<T>(
    val success: Boolean,
    val message: String,
    val status: String,
    val entity: T?
)