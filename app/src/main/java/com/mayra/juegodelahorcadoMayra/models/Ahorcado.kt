package com.mayra.juegodelahorcadoMayra.models

import java.util.*

class Ahorcado(private val palabraSecreta: String) {

    public var intentosRestantes = 6
    private var letrasUsadas = mutableListOf<Char>()
    private var palabraAdivinada = MutableList(palabraSecreta.length) {
        if (palabraSecreta[it] == ' ') ' ' else '_'
    }

    fun intentarLetra(letra: Char): Boolean {
        val letraNormalizada = normalizarLetra(letra)
        if (letrasUsadas.contains(letraNormalizada)) {
            return false // La letra ya ha sido usada
        }
        letrasUsadas.add(letraNormalizada)
        var acierto = false
        for (i in palabraSecreta.indices) {
            val letraPalabra = normalizarLetra(palabraSecreta[i])
            if (letraPalabra == letraNormalizada) {
                palabraAdivinada[i] = palabraSecreta[i]
                acierto = true
            }
        }
        if (!acierto) {
            intentosRestantes--
        }
        return acierto
    }

    private fun normalizarLetra(letra: Char): Char {
        val caracter = letra.toString().toLowerCase(Locale.getDefault())[0]
        return when (caracter) {
            'á', 'à', 'ä' -> 'a'
            'é', 'è', 'ë' -> 'e'
            'í', 'ì', 'ï' -> 'i'
            'ó', 'ò', 'ö' -> 'o'
            'ú', 'ù', 'ü' -> 'u'
            else -> caracter
        }
    }

    fun estaCompleto(): Boolean {
        return intentosRestantes == 0 || !palabraAdivinada.contains('_')
    }

    fun resultado(): Boolean = intentosRestantes != 0

    fun dibujarPalabraAdivinada(): String {
        return palabraAdivinada.joinToString(" ")
    }

    fun getWordSecret(): String = palabraSecreta
}