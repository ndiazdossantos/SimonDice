package com.dam2.simondice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val otraClase by viewModels<MyViewModel>()

        val play = findViewById<Button>(R.id.playButton)
        val checklist = findViewById<Button>(R.id.checkButton)
        val blue = findViewById<Button>(R.id.blueButton)
        val red = findViewById<Button>(R.id.redButton)
        val yellow = findViewById<Button>(R.id.yellowButton)
        val green = findViewById<Button>(R.id.greenButton)

        val toast = Toast.makeText(applicationContext, "Acierto! Continua jugando!", Toast.LENGTH_SHORT)
        val toast2 = Toast.makeText(applicationContext, "Inicio", Toast.LENGTH_SHORT)
        val toast3 = Toast.makeText(applicationContext, "Error, juego finalizado", Toast.LENGTH_SHORT)

        // contenido para indicar la ronda actual
        val ronda = findViewById<TextView>(R.id.ronda)
        val textoRonda = "Ronda: "


        // solucionado error, hay que tener los botones ordenados como la lista o te pintara unos de otros colores
        val botones = listOf(red, green, yellow, blue)

        otraClase.listaReto.observe(this, Observer {
            otraClase.mostrarSecuencia(botones)
            ronda.text = "$textoRonda ${otraClase.listaReto.value?.size}"

        })

        play.setOnClickListener {
            otraClase.inicio()
            toast2.show()
        }

        checklist.setOnClickListener {
            if (!otraClase.compararSecuencia())
                toast.show()
            else {
                toast3.show()
            }
        }

        red.setOnClickListener {
            otraClase.gardarSecuencia(1)
        }
        green.setOnClickListener {
            otraClase.gardarSecuencia(2)
        }
        yellow.setOnClickListener {
            otraClase.gardarSecuencia(3)
        }
        blue.setOnClickListener {
            otraClase.gardarSecuencia(4)
        }

    }
}
