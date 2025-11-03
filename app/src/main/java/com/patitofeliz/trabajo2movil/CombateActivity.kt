package com.patitofeliz.trabajo2movil

import android.graphics.Bitmap
import android.graphics.Matrix
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.patitofeliz.trabajo2movil.combate.Combate
import com.patitofeliz.trabajo2movil.model.unidades.Unidad

class CombateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_combate)

        // Mapeamos los IV
        var atacante1:ImageView = findViewById(R.id.ivAtacante1)
        var atacante2:ImageView = findViewById(R.id.ivAtacante2)

        // Seteamos las imagenes
        val unidad1 = Combate.getAtacante1()
        val unidad2 = Combate.getAtacante2()

        unidad1.idleState(atacante1)
        unidad2.idleState(atacante2)

        var pv1: TextView = findViewById(R.id.tvHP1)
        var pv2:TextView = findViewById(R.id.tvHP2)

        pv1.text = unidad1.hp.toString()
        pv2.text = unidad2.hp.toString()

        // Botones
        var btnAtacar1:Button = findViewById(R.id.btnAtacar1)
        var btnAtacar2:Button = findViewById(R.id.btnAtacar2)

        btnAtacar1.setOnClickListener {
            atacar(unidad1, atacante1)
        }

        btnAtacar2.setOnClickListener {
            atacar(unidad2, atacante2)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun atacar(unidad: Unidad, imageView: ImageView)
    {
        unidad.atacarState(imageView)
    }
}