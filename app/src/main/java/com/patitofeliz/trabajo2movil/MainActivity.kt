package com.patitofeliz.trabajo2movil

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        var estado: Boolean = false;

        // Mapeamos el listView
        val listViewMPrincipal: ListView = findViewById(R.id.lvMenuPrincipal);

        // Crearemos un array con las opciones
        val opcionesMPrincipal: Array<String> = arrayOf("Test1", "Test2");

        val opcionesMPrincipal2: Array<String> = arrayOf("Test3", "Test4");

        // El adaptador para el lv
        val adaptadorLvMPrincipal = ArrayAdapter(this, android.R.layout.simple_list_item_1, opcionesMPrincipal)
        val adaptadorLvMPrincipal2 = ArrayAdapter(this, android.R.layout.simple_list_item_1, opcionesMPrincipal2);

        // Asociamos el adaptador
        if (!estado)
            listViewMPrincipal.adapter = adaptadorLvMPrincipal;
        else
            listViewMPrincipal.adapter = adaptadorLvMPrincipal2;

        listViewMPrincipal.setOnItemClickListener { parent, view, position, id ->
            val opcSeleccionada = parent.getItemAtPosition(position).toString()

            val currentOptions = if (!estado) opcionesMPrincipal else opcionesMPrincipal2

            if (opcSeleccionada == currentOptions[position]) {
                println("Seleccionaste $opcSeleccionada")
            }

            if (position == 0) {
                estado = !estado

                val newAdapter = if (!estado) adaptadorLvMPrincipal else adaptadorLvMPrincipal2
                listViewMPrincipal.adapter = newAdapter
            }
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}