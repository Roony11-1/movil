package com.patitofeliz.trabajo2movil

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.patitofeliz.trabajo2movil.config.RetrofitClient
import com.patitofeliz.trabajo2movil.model.LoginRequest
import com.patitofeliz.trabajo2movil.model.Response
import com.patitofeliz.trabajo2movil.model.Sesion
import com.patitofeliz.trabajo2movil.model.Usuario
import com.patitofeliz.trabajo2movil.model.unidades.clases.Espadachin
import com.patitofeliz.trabajo2movil.model.unidades.clases.Jinete
import com.patitofeliz.trabajo2movil.model.unidades.clases.Mirmidon
import com.patitofeliz.trabajo2movil.sistemaunidad.UnidadController
import retrofit2.Call
import retrofit2.Callback
import kotlin.math.log

class MainActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Registro de clases
        // lo externalizare en su momento
        ClaseRegistro.registrar("Mirmidón") { Mirmidon() }
        ClaseRegistro.registrar("Jinete") { Jinete() }
        ClaseRegistro.registrar("Espadachin", { Espadachin() })

        // Mapeamos
        // Botones
        var botonLogear: Button = findViewById(R.id.btnLogin);
        var botonRegistrar: Button = findViewById(R.id.btnRegister);
        var btnInvitado: Button = findViewById(R.id.btnInvitado)
        // EditText
        var etEmail: EditText = findViewById(R.id.etEmail);
        var etContraseña: EditText = findViewById(R.id.etPassword);

        // Intentar Logear
        botonLogear.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val contraseña = etContraseña.text.toString().trim()

            if (email.isEmpty() || contraseña.isEmpty())
            {
                Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val loginRequest: LoginRequest = LoginRequest(email, contraseña)

            RetrofitClient.usuarioService.loginUsuario(loginRequest)
                .enqueue(object : Callback<Response<Usuario>> {
                    override fun onResponse(
                        call: Call<Response<Usuario>>,
                        response: retrofit2.Response<Response<Usuario>>
                    ) {
                        if (response.isSuccessful) {
                            val res = response.body()
                            Toast.makeText(this@MainActivity, res?.message, Toast.LENGTH_SHORT).show()
                            if (res?.entity != null)
                            {
                                val sesion = Sesion(this@MainActivity)
                                sesion.guardarUsuario(res.entity)
                                UnidadController.limpiarLista()
                                val intent = Intent(this@MainActivity, PrincipalActivity::class.java)
                                startActivity(intent)
                                finish()
                            }

                        } else {
                            Toast.makeText(this@MainActivity, "Error en la respuesta del servidor", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<Response<Usuario>>, t: Throwable) {
                        Toast.makeText(this@MainActivity, "Error de conexión: ${t.message}", Toast.LENGTH_SHORT).show()
                    }
                })
        }

        // Ir a registrarse
        botonRegistrar.setOnClickListener {
            var irVentanaRegistro = Intent(this, RegisterActivity::class.java)

            startActivity(irVentanaRegistro)
            finish()
        }

        btnInvitado.setOnClickListener {
            val sesion = Sesion(this)
            sesion.cerrarSesion()

            val intent = Intent(this@MainActivity, PrincipalActivity::class.java)
            startActivity(intent)
            finish()
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}