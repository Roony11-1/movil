package com.patitofeliz.trabajo2movil.model

import android.content.Context
import android.content.SharedPreferences
import com.patitofeliz.trabajo2movil.model.Usuario

class Sesion(context: Context)
{
    private val prefs: SharedPreferences = context.getSharedPreferences("SesionUsuario", Context.MODE_PRIVATE)

    fun guardarUsuario(usuario: Usuario)
    {
        val editor = prefs.edit()
        editor.putInt("id", usuario.id ?: -1)
        editor.putString("nombreUsuario", usuario.nombreUsuario)
        editor.putString("email", usuario.email)
        editor.putString("contraseña", usuario.contraseña)
        editor.putString("telefono", usuario.telefono)
        editor.putString("tipo", usuario.tipo)
        editor.putString("profilePhoto", usuario.profilePhoto)
        editor.apply()
    }

    fun obtenerUsuario(): Usuario?
    {
        val id = prefs.getInt("id", -1)
        val nombreUsuario = prefs.getString("nombreUsuario", null)
        val email = prefs.getString("email", null)
        val contraseña = prefs.getString("contraseña", null)
        val telefono = prefs.getString("telefono", null)
        val tipo = prefs.getString("tipo", null)
        val profilePhoto = prefs.getString("profilePhoto", null)

        return if (id != -1 && nombreUsuario != null && email != null)
            Usuario(id, nombreUsuario, email, contraseña, telefono, null, tipo, profilePhoto)
        else
            null
    }

    fun cerrarSesion()
    {
        prefs.edit().clear().apply()
    }

    fun estaLogueado(): Boolean
    {
        return prefs.contains("id")
    }
}