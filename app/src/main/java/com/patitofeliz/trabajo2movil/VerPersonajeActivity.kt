package com.patitofeliz.trabajo2movil

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.ListView
import android.widget.SimpleAdapter
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.patitofeliz.trabajo2movil.model.unidades.Unidad
import com.patitofeliz.trabajo2movil.sistemaunidad.UnidadController

class VerPersonajeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ver_personaje)


        // Mapeos
        var lvPersonajes: ListView = findViewById(R.id.lvVerPersonajes)
        var btnVolver: Button = findViewById(R.id.btnVolverPrincipal)

        var personajes: List<Unidad> = UnidadController.obtenerPersonajes()
        val atributos: List<Map<String, String>> = personajes.map { mapOf("nombre" to "Nombre: ${it.nombre}", "clase" to "Clase: ${it.clase?.nombreClase}") }

        val adaptadorPersonajes = SimpleAdapter(
            this,
            atributos,
            android.R.layout.simple_list_item_2,
            arrayOf("nombre", "clase"),
            intArrayOf(android.R.id.text1, android.R.id.text2)
        )

        lvPersonajes.adapter = adaptadorPersonajes

        lvPersonajes.setOnItemClickListener { _, _, position, _ ->
            val personaje = personajes[position]

            val info = """
                Nombre: ${personaje.nombre}
                -- Nivel --
                Nivel: ${personaje.nivel.nivel}
                Experiencia: ${personaje.nivel.experiencia}
                -- Clase --
                Clase: ${personaje.clase?.nombreClase ?: "Sin clase"}
                PV: ${personaje.estadisticasUnidad.pv ?: "N/A"}
                Fuerza: ${personaje.estadisticasUnidad.fuerza ?: "N/A"}
                Habilidad: ${personaje.estadisticasUnidad.habilidad ?: "N/A"}
                Velocidad: ${personaje.estadisticasUnidad.velocidad ?: "N/A"}
                Defensa: ${personaje.estadisticasUnidad.defensa ?: "N/A"}
                Resistencia: ${personaje.estadisticasUnidad.resistencia ?: "N/A"}
                Constitución: ${personaje.estadisticasUnidad.constitucion ?: "N/A"}
        """.trimIndent()

            val dialogView = layoutInflater.inflate(R.layout.dialog_personaje_info, null)
            val imgClase = dialogView.findViewById<ImageView>(R.id.imgClase)
            val tvInfo = dialogView.findViewById<TextView>(R.id.tvInfoPersonaje)

            tvInfo.text = info
            personaje.clase?.sprites?.imgScreen?.let { imgClase.setImageResource(it) }

            AlertDialog.Builder(this)
                .setTitle("Información del personaje")
                .setView(dialogView)
                .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
                .show()
        }

        btnVolver.setOnClickListener {
            val intent = Intent(this@VerPersonajeActivity, PrincipalActivity::class.java)
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