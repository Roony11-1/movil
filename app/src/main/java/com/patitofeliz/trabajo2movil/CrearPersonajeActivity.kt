package com.patitofeliz.trabajo2movil

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.patitofeliz.trabajo2movil.model.Sesion
import com.patitofeliz.trabajo2movil.model.unidades.Unidad
import com.patitofeliz.trabajo2movil.model.unidades.clases.Clase
import com.patitofeliz.trabajo2movil.model.unidades.clases.Jinete
import com.patitofeliz.trabajo2movil.model.unidades.clases.Mirmidon
import com.patitofeliz.trabajo2movil.sistemaunidad.UnidadController

class CrearPersonajeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_crear_personaje)

        ClaseRegistro.registrar("Mirmidón") { Mirmidon() }
        ClaseRegistro.registrar("Jinete") { Jinete() }

        val sesion = Sesion(this)

        val clases = ClaseRegistro.obtenerTodas()
        val nombresClases = ClaseRegistro.obtenerTodas().map { it.nombre }


        var btnCrear: Button = findViewById(R.id.btnCrearPersonaje)
        var btnVolver: Button = findViewById(R.id.btnVolverPrincipal)
        var etNombre: EditText = findViewById(R.id.etnombrePersonaje)

        val spClases: Spinner = findViewById(R.id.spClases)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, nombresClases)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spClases.adapter = adapter

        btnCrear.setOnClickListener {
            var cantidadPersonajes: Int? = UnidadController.obtenerPersonajes().size
            val clase = ClaseRegistro.obtenerTodas()[spClases.selectedItemPosition].crear()
            var idUsuario: Int? = null
            if (sesion.estaLogueado())
            {
                val usuarioActual = sesion.obtenerUsuario()
                idUsuario = usuarioActual?.id
                cantidadPersonajes = null
            }
            else
            {
                idUsuario = null
                cantidadPersonajes =+ 1
            }

            val personaje = Unidad(cantidadPersonajes, etNombre.text.toString(), clase,1,idUsuario, clase.estadisticasBase)

            UnidadController.agregarPersonaje(personaje)

            //if (sesion.estaLogueado())
                // aqui guardaremos la unidad

            Toast.makeText(this, "Personaje creado - Nombre: ${personaje.nombre} - Clase: ${personaje.clase?.nombreClase}", Toast.LENGTH_SHORT).show()
        }

        btnVolver.setOnClickListener {
            val intent = Intent(this@CrearPersonajeActivity, PrincipalActivity::class.java)
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