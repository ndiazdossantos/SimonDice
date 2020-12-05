package com.dam2.simondice

import android.content.res.ColorStateList
import android.graphics.Color
import android.widget.Button
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MyViewModel : ViewModel() {


    // El MutableLiveData almacena la información para que no se pierda, cuando estamos usando los ViewModel

    var listaReto = MutableLiveData<MutableList<Int>>()
    var listaUsuario = MutableLiveData<MutableList<Int>>()

    // nos será saber el estado del juego para mostrar o no "x" funciones
    var estado  = MutableLiveData<Boolean>()

    init {
        listaReto.value = mutableListOf()
        listaUsuario.value = mutableListOf()
    }


    // hacemos un metodo para restablecer todos los datos almacenados a 0 y poder iniciar sin nada en memoria
    fun borrar(){
        listaReto.value?.clear()
        listaUsuario.value?.clear()
    }

    // el ? en el value verifica si hay algo almacenado o no

     fun engadirValor(){
        val numero = Random.nextInt(4) + 1
        listaReto.value?.add(numero)
        listaReto.postValue(listaReto.value)
    }

    // el postValue avisa al observador de que cambió un valor para ejecutar el trozo de código una vez haya detectado este cambio

    // el juego esta empezando, borra lo anterior y añade el valor de la lista de colores random

    fun inicio(){
        estado.value = false;
        borrar()
        engadirValor()
    }

    // guardamos el numero(asignado a un color) introducido por el usuario

    fun gardarSecuencia(color: Int) {
        when (color) {
            1 -> listaUsuario.value?.add(1)
            2 -> listaUsuario.value?.add(2)
            3 -> listaUsuario.value?.add(3)
            else -> listaUsuario.value!!.add(4)
        }
    }

    // si el valor de la lista dada de colores es igual a la introducida por la del usuario, el juego continua
    fun compararSecuencia() : Boolean{
        var variable = false;
       if(listaReto.value == listaUsuario.value){
           listaUsuario.value?.clear()
           engadirValor()
       }else{
           estado.value = true;
           variable = true;
           borrar()
       }
        return variable
    }

    fun mostrarSecuencia(listaBotones: List<Button>) {
        CoroutineScope(Dispatchers.Main).launch {
            for (colors in listaReto.value!!) {
               
                listaBotones[colors-1].backgroundTintList = ColorStateList.valueOf(Color.parseColor("#000000"))
                delay(1000)
                when (colors) {
                    1-> listaBotones[colors-1].backgroundTintList = ColorStateList.valueOf(Color.parseColor("#F10909"))
                    2-> listaBotones[colors-1].backgroundTintList = ColorStateList.valueOf(Color.parseColor("#16E81E"))
                    3-> listaBotones[colors-1].backgroundTintList = ColorStateList.valueOf(Color.parseColor("#F4DD0F"))
                    4-> listaBotones[colors-1].backgroundTintList = ColorStateList.valueOf(Color.parseColor("#2196F3"))
                }
            }
        }
    }


}