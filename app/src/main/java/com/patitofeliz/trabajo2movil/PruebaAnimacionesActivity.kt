package com.patitofeliz.trabajo2movil

import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.SimpleAdapter
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.patitofeliz.trabajo2movil.model.unidades.Unidad
import com.patitofeliz.trabajo2movil.sistemaunidad.UnidadController

class PruebaAnimacionesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_prueba_animaciones)

        val spAccion: Spinner = findViewById(R.id.spPAAnimacion)
        val spSeleccion: Spinner = findViewById(R.id.spPASpinner)
        val spriteClase = findViewById<ImageView>(R.id.ivPA)

        // ──────────────── PERSONAJES ────────────────
        val personajes: List<Unidad> = UnidadController.obtenerPersonajes()

        val atributos: List<Map<String, String>> = personajes.map {
            mapOf(
                "nombre" to "Nombre: ${it.nombre}",
                "clase" to "Clase: ${it.clase?.nombreClase}"
            )
        }

        val adaptadorPersonajes = SimpleAdapter(
            this@PruebaAnimacionesActivity,
            atributos,
            android.R.layout.simple_list_item_2,
            arrayOf("nombre", "clase"),
            intArrayOf(android.R.id.text1, android.R.id.text2)
        )

        spSeleccion.adapter = adaptadorPersonajes

        // ──────────────── ACCIONES ────────────────
        val arrAccion = arrayOf("Idle", "Ataque", "Esquiva")
        val adaptadorAccion = ArrayAdapter(this, android.R.layout.simple_list_item_1, arrAccion)
        spAccion.adapter = adaptadorAccion

        // ──────────────── SELECCIONES ────────────────
        var unidadSeleccionada: Unidad? = null

        // Cuando cambias de personaje
        spSeleccion.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: android.view.View?,
                position: Int,
                id: Long
            ) {
                unidadSeleccionada = personajes[position]
                // Mostrar sprite inicial (idle)
                unidadSeleccionada?.clase?.idleState(spriteClase)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        // Cuando cambias de acción
        spAccion.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: android.view.View?,
                position: Int,
                id: Long
            ) {
                val accionSeleccionada = arrAccion[position]

                when (accionSeleccionada)
                {
                    "Idle" -> unidadSeleccionada?.idleState(spriteClase)
                    "Ataque" -> unidadSeleccionada?.atacarState(spriteClase, null)
                    "Esquiva" -> unidadSeleccionada?.dodgeState(spriteClase)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        // Ajuste de insets (para full screen con barras del sistema)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
