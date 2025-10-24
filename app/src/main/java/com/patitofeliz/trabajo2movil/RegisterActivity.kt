package com.patitofeliz.trabajo2movil

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.patitofeliz.trabajo2movil.config.RetrofitClient
import com.patitofeliz.trabajo2movil.model.Response
import com.patitofeliz.trabajo2movil.model.Usuario
import com.patitofeliz.trabajo2movil.model.ubicacion.Comuna
import com.patitofeliz.trabajo2movil.model.ubicacion.Region
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)

        fun limpiarETText(et: EditText): String
        {
            return et.text.toString().trim()
        }

        var irVentanaLogin = Intent(this, MainActivity::class.java)
        lateinit var comunasActuales: List<Comuna>

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
        var spRegion: Spinner = findViewById(R.id.spRegion)
        var spComuna: Spinner = findViewById(R.id.spComuna)

        fun llenarSpinners(regiones: List<Region>) {
            val nombresRegiones = regiones.map { it.nombreRegion }
            val adapterRegiones = ArrayAdapter(this, android.R.layout.simple_spinner_item, nombresRegiones)
            adapterRegiones.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spRegion.adapter = adapterRegiones

            spRegion.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val comunas = regiones[position].comunas
                    comunasActuales = comunas
                    val nombresComunas = comunas.map { it.nombreComuna ?: "Sin nombre" }
                    val adapterComunas =
                        ArrayAdapter(this@RegisterActivity, android.R.layout.simple_spinner_item, nombresComunas)
                    adapterComunas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spComuna.adapter = adapterComunas
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
        }

        // Lenaremos los spiners
        RetrofitClient.ubicacionService.getRegiones()
            .enqueue(object : retrofit2.Callback<List<Region>> {
                override fun onResponse(
                    call: Call<List<Region>>,
                    response: retrofit2.Response<List<Region>>
                ) {
                    if (response.isSuccessful) {
                        val regiones = response.body() ?: emptyList()
                        llenarSpinners(regiones)
                    } else {
                        Toast.makeText(
                            this@RegisterActivity,
                            "Error al cargar regiones",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<List<Region>>, t: Throwable) {
                    Toast.makeText(
                        this@RegisterActivity,
                        "Error de conexión: ${t.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })

        btnRegistrar.setOnClickListener {
            val nombreUsuario: String = limpiarETText(etNombreUsuario)
            val email: String = limpiarETText(etEmail)
            val confirmarEmail: String = limpiarETText(etConfirmarEmail)
            val contraseña: String = limpiarETText(etContraseña)
            val confirmarContraseña: String = limpiarETText(etConfirmarContraseña)
            val telefono: String = limpiarETText(etTelefono)
            val comunaSeleccionada: Comuna = comunasActuales[spComuna.selectedItemPosition]

            if (nombreUsuario.isEmpty() || email.isEmpty() || confirmarEmail.isEmpty() || contraseña.isEmpty()
                || confirmarContraseña.isEmpty() || telefono.isEmpty())
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

            val usuario: Usuario = Usuario(nombreUsuario, email, contraseña, telefono, comunaSeleccionada, "usuario", "none")

            RetrofitClient.usuarioService.registrarUsuario(usuario)
                .enqueue(object : Callback<Response<Usuario>> {
                    override fun onResponse(
                        call: Call<Response<Usuario>>,
                        response: retrofit2.Response<Response<Usuario>>
                    ) {
                        if (response.isSuccessful) {
                            val res = response.body()
                            Toast.makeText(this@RegisterActivity, res?.message, Toast.LENGTH_SHORT).show()
                            if (res?.success == true)
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