package com.patitofeliz.trabajo2movil.model.unidades.clases

import android.os.Handler
import android.os.Looper
import android.widget.ImageView

class Sprites(var imgScreen: Int,
    var idle: Int,
    var ataque: List<Int> = emptyList(),
    val delaysAtaque: List<Long>,
    var dodge: List<Int> = emptyList(),
    val delaysDodge: List<Long>? = null)
{
    private val handler = Handler(Looper.getMainLooper())

    private fun mostrarAnimacion(
        imageView: ImageView,
        frames: List<Int>,
        delays: List<Long>? = null,
        onFinish: (() -> Unit)? = null)
    {
        if (frames.isEmpty()) return
        var frameIndex = 0

        val runnable = object : Runnable
        {
            override fun run()
            {
                imageView.setImageResource(frames[frameIndex])
                frameIndex++
                if (frameIndex < frames.size)
                {
                    val delay = delays?.getOrNull(frameIndex - 1) ?: 100L
                    handler.postDelayed(this, delay)
                }
                else
                    onFinish?.invoke()
            }
        }

        handler.post(runnable)
    }

    fun idle(imageView: ImageView)
    {
        imageView.setImageResource(idle)
    }

    fun atacar(imageView: ImageView)
    {
        mostrarAnimacion(imageView, ataque, delaysAtaque)
        {
            idle(imageView)
        }
    }

    fun dodge(imageView: ImageView)
    {
        mostrarAnimacion(imageView, dodge, delaysDodge)
        {
            idle(imageView)
        }
    }
}