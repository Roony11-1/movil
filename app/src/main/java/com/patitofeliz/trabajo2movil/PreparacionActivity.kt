package com.patitofeliz.trabajo2movil

import android.os.Bundle
import android.widget.Button
import android.widget.SimpleAdapter
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.patitofeliz.trabajo2movil.combate.Combate
import com.patitofeliz.trabajo2movil.model.unidades.Unidad
import com.patitofeliz.trabajo2movil.sistemaunidad.UnidadController

class PreparacionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_preparacion)

        // Aqui sera el combate
        // Primero debera seleccionar el personaje
        // mapeamos el spinner
        var spSeleccion:Spinner = findViewById(R.id.spCombateSeleccionarPersonaje)

        // Obtenemos la lista de personajes
        var personajes: List<Unidad> = UnidadController.obtenerPersonajes()

        // Creamos un adaptador
        val atributos: List<Map<String, String>> = personajes.map { mapOf("nombre" to "Nombre: ${it.nombre}", "clase" to "Clase: ${it.clase?.nombreClase}") }

        val adaptadorPersonajes = SimpleAdapter(this@PreparacionActivity,
            atributos,
            android.R.layout.simple_list_item_2,
            arrayOf("nombre", "clase"),
            intArrayOf(android.R.id.text1, android.R.id.text2))

        spSeleccion.adapter = adaptadorPersonajes

        // Mapeamos los botones
        var btnLocal:Button = findViewById(R.id.btnCombateLocal)

        btnLocal.setOnClickListener {
            // Obtener el personaje seleccionado
            val personajeSeleccionado = personajes[spSeleccion.selectedItemPosition]

            // Crear un enemigo aleatorio
            val enemigo = Combate.crearCombatiente()

            // Configuramos el mensaje para el debug
            val mensaje = """
            Nombre: ${personajeSeleccionado.nombre} - Clase: ${personajeSeleccionado.clase?.nombreClase}
    
            VS
    
            Nombre: ${enemigo.nombre} - Clase: ${enemigo.clase?.nombreClase}
            """.trimIndent()

            AlertDialog.Builder(this)
                .setTitle("Combate local")
                .setMessage(mensaje)
                .setPositiveButton("Aceptar", null)
                .show()

            // Deberia crear en el objeto la batalla¿deberia agregarle interfaz?
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}