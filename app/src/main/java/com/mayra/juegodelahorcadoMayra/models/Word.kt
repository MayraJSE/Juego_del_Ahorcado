package com.mayra.juegodelahorcadoMayra.models

class Word {
    private val list = listOf<String>(
        "El cotorro azul habla",
        "Que tengas un excelente dia",
        "El gato con botas",
        "Y retiemble en su centro la tierra ",
        "Y donde estan las rubias",
        "Somos tecnm",
        "La vida es un riesgo",
        "La aventura nos aguarda",
        "La felicidad no es tener lo que quieres es querer lo que tienes",
        "Si puedes soñarlo puedes lograrlo",
        "Siembra un arbol y haras feliz a un perro"
    )

    fun getWordRandom(): String {
        return list.random()
    }
}