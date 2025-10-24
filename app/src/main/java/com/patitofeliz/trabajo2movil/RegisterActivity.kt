package com.patitofeliz.trabajo2movil

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.patitofeliz.trabajo2movil.config.RetrofitClient
import com.patitofeliz.trabajo2movil.model.Response
import com.patitofeliz.trabajo2movil.model.Usuario
import retrofit2.Call
import retrofit2.Callback

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)

        // Botones
        var btnVISesion: Button = findViewById(R.id.btnVolverLogin)
        var btnRegistrar: Button = findViewById(R.id.btnRegistrar)
        // EditText
        var etNombreUsuario: EditText = findViewById(R.id.etNombreUsuario)
        var etEmail: EditText = findViewById(R.id.etEmail)
        var etConfirmarEmail: EditText = findViewById(R.id.etConfirmarEmail)
        var etContraseña: EditText = findViewById(R.id.etContraseña)
        var etConfirmarContraseña: EditText = findViewById(R.id.etConfirmarContraseña)
        var etTelefono: EditText = findViewById(R.id.etTelefono)
        var etRegion: EditText = findViewById(R.id.etRegion)
        var etComuna: EditText = findViewById(R.id.etComuna)

        var irVentanaLogin = Intent(this, MainActivity::class.java)

        fun limpiarETText(et: EditText): String
        {
            return et.text.toString().trim()
        }

        fun limpiarET(et: EditText)
        {
            et.text.clear()
        }

        btnRegistrar.setOnClickListener {
            val nombreUsuario: String = limpiarETText(etNombreUsuario)
            val email: String = limpiarETText(etEmail)
            val confirmarEmail: String = limpiarETText(etConfirmarEmail)
            val contraseña: String = limpiarETText(etContraseña)
            val confirmarContraseña: String = limpiarETText(etConfirmarContraseña)
            val telefono: String = limpiarETText(etTelefono)
            val region: String = limpiarETText(etRegion)
            val comuna: String = limpiarETText(etComuna)

            if (nombreUsuario.isEmpty() || email.isEmpty() || confirmarEmail.isEmpty() || contraseña.isEmpty()
                || confirmarContraseña.isEmpty() || telefono.isEmpty() || region.isEmpty() || comuna.isEmpty())
            {
                Toast.makeText(this, "Por favor completa todos los campos obligatorios", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!email.equals(confirmarEmail))
            {
                Toast.makeText(this, "Los correos no coinciden", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!contraseña.equals(confirmarContraseña))
            {
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val usuario: Usuario = Usuario(nombreUsuario, email, contraseña, telefono, region, comuna, "usuario", "none")

            RetrofitClient.instance.registrarUsuario(usuario)
                .enqueue(object : Callback<Response<Usuario>> {
                    override fun onResponse(
                        call: Call<Response<Usuario>>,
                        response: retrofit2.Response<Response<Usuario>>
                    ) {
                        if (response.isSuccessful) {
                            val res = response.body()
                            Toast.makeText(this@RegisterActivity, res?.message, Toast.LENGTH_SHORT).show()
                            if (res?.success ?: true)
                                startActivity(irVentanaLogin)

                        } else {
                            Toast.makeText(this@RegisterActivity, "Error en la respuesta del servidor", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<Response<Usuario>>, t: Throwable) {
                        Toast.makeText(this@RegisterActivity, "Error de conexión: ${t.message}", Toast.LENGTH_SHORT).show()
                    }
                })



        }

        btnVISesion.setOnClickListener {
            startActivity(irVentanaLogin)
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}