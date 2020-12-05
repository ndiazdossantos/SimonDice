package com.dam2.simondice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
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

        val toast = Toast.makeText(applicationContext,"Juego Finalizado", Toast.LENGTH_SHORT)
        val toast2 = Toast.makeText(applicationContext,"Inicio", Toast.LENGTH_SHORT)

        val botones = listOf(red,blue,green,yellow)

       otraClase.listaReto.observe(this, Observer{
            otraClase.mostrarSecuencia(botones)
        })

        play.setOnClickListener{
            otraClase.inicio()
            toast2.show()
        }

        checklist.setOnClickListener{
            if (!otraClase.compararSecuencia())
                toast.show()
        }

        red.setOnClickListener{
            otraClase.gardarSecuencia(1)
        }
        green.setOnClickListener{
            otraClase.gardarSecuencia(2)
        }
        yellow.setOnClickListener{
            otraClase.gardarSecuencia(3)
        }
        blue.setOnClickListener{
            otraClase.gardarSecuencia(4)
        }

   /*     play.setOnClickListener{
            finished = false
            borrar(game,player)
            engadirSecuencia(game)
            toast2.show()
            mostrarSecuencia(game)
        }

    */
    /*    checklist.setOnClickListener{
            if(finished==false){
                if(comprobarSecuencia(game,player)){
                    engadirSecuencia(game)
                    player.clear()
                    mostrarSecuencia(game)
                }else{
                    finished=true
                    toast.show()
                }
            }
        }
        red.setOnClickListener{
            gardarSecuencia(player,1)
        }
        green.setOnClickListener{
            gardarSecuencia(player,2)
        }
        yellow.setOnClickListener{
            gardarSecuencia(player,3)
        }
        blue.setOnClickListener{
            gardarSecuencia(player,4)
        }

     */
    }

  /*  fun engadirSecuencia(secuencia : MutableList<Int>)  {
        val numb= Random.nextInt(4) + 1
        secuencia.add(numb)
    }

    fun comprobarSecuencia(secuencia : MutableList<Int>, secuenciaUsuario : MutableList<Int>) : Boolean {
        return secuencia == secuenciaUsuario
    }

    fun borrar(secuencia: MutableList<Int>, secuenciaUsuario: MutableList<Int>){
        secuencia.clear()
        secuenciaUsuario.clear()
    }

    fun gardarSecuencia(secuenciaUsuario: MutableList<Int>, color: Int){
        when(color){
            1 -> secuenciaUsuario.add(1)
            2 -> secuenciaUsuario.add(2)
            3 -> secuenciaUsuario.add(3)
            else -> secuenciaUsuario.add(4)
        }
    }

    fun mostrarSecuencia(sec: MutableList<Int>){
        var t = Toast.makeText(applicationContext,"Inicio", Toast.LENGTH_SHORT)
        for (color in sec){
            when(color){
                1 -> Toast.makeText(applicationContext,"Rojo", Toast.LENGTH_SHORT).show()
                2 -> Toast.makeText(applicationContext,"Verde", Toast.LENGTH_SHORT).show()
                3 -> Toast.makeText(applicationContext,"Amarillo", Toast.LENGTH_SHORT).show()
                4 -> Toast.makeText(applicationContext,"Azul", Toast.LENGTH_SHORT).show()
            }
        }
    }

   */
}
