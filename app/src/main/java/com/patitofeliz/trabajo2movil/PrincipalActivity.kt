package com.patitofeliz.trabajo2movil

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.patitofeliz.trabajo2movil.model.Sesion
import com.patitofeliz.trabajo2movil.model.unidades.Unidad
import com.patitofeliz.trabajo2movil.sistemaunidad.UnidadController

class PrincipalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_principal)

        val sesion = Sesion(this)
        val usuario = sesion.obtenerUsuario()
        var personajes: List<Unidad> = UnidadController.obtenerPersonajes()

        fun verificarSizePersonajes(unidades:List<Unidad>): Boolean
        {
            if (unidades.size > 0)
                return true
            else
            {
                Toast.makeText(this, "Primero debes tener unidades", Toast.LENGTH_SHORT).show()
                return false
            }

        }

        // Mapeamos el texto
        val tvSaludo: TextView = findViewById(R.id.tvSaludo)

        if (usuario != null)
            tvSaludo.text = "Bienvenido ${usuario.nombreUsuario}"
        else
            tvSaludo.text = "Estas sin una sesion activa"


        // Mapeamos el lv principal
        val lvPrinciapl: ListView = findViewById(R.id.lvMenuPrincipal)

        val arrMenu: Array<String> = if (Sesion(this).estaLogueado())
        {
            arrayOf(
                "Crear Personaje",
                "Ver Personajes",
                "Cerrar Sesión"
            )
        }
        else
        {
            arrayOf(
                "Crear Personaje",
                "Ver Personajes",
                "Combatir",
                "Volver a pantalla principal"
            )
        }

        val adaptadorPrincipal = ArrayAdapter(this, android.R.layout.simple_list_item_1, arrMenu)
        lvPrinciapl.adapter = adaptadorPrincipal

        lvPrinciapl.setOnItemClickListener { parent, view, position, id ->
            val opcionSeleccionada = parent.getItemAtPosition(position).toString()

            if (opcionSeleccionada.equals("Cerrar Sesión") || opcionSeleccionada.equals("Volver a pantalla principal"))
            {
                val sesion = Sesion(this)
                if (sesion.estaLogueado())
                {
                    sesion.cerrarSesion()
                    UnidadController.limpiarLista()
                }

                val intent = Intent(this@PrincipalActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }

            if (opcionSeleccionada.equals("Crear Personaje"))
            {
                val intent = Intent(this@PrincipalActivity, CrearPersonajeActivity::class.java)
                startActivity(intent)
            }

            if (opcionSeleccionada.equals("Ver Personajes"))
            {
                if (verificarSizePersonajes(personajes))
                {
                    val intent = Intent(this@PrincipalActivity, VerPersonajeActivity::class.java)
                    startActivity(intent)
                }
            }

            if (opcionSeleccionada.equals("Combatir"))
            {
                if (verificarSizePersonajes(personajes))
                {
                    val intent = Intent(this@PrincipalActivity, CombateActivity::class.java)
                    startActivity(intent)
                }
            }

        }
        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}