package com.mayra.juegodelahorcadoMayra.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.ollin.juegodelahorcado.R
import com.ollin.juegodelahorcado.databinding.ActivityGameBinding
import com.mayra.juegodelahorcadoMayra.models.Ahorcado
import com.mayra.juegodelahorcadoMayra.models.Word


class GameActivity : AppCompatActivity() {
    private lateinit var ahorcado: Ahorcado
    private lateinit var words: Word
    private lateinit var binding: ActivityGameBinding
    private lateinit var imagenes: IntArray
    private lateinit var btnPressList: MutableList<View>
    lateinit var namePlayer: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recibir = intent
        namePlayer = recibir.getStringExtra("nameplayer")!!
        println("Jugador $namePlayer")
        btnPressList = mutableListOf()
        words = Word()
        ahorcado = Ahorcado(words.getWordRandom())

        appLoad()
    }

    fun reloadGame() {
        ahorcado = Ahorcado(words.getWordRandom())
        appLoad()
        changeStateButton()
    }

    fun appLoad() {
        var palabra = ahorcado.dibujarPalabraAdivinada()

    }

    fun changeIMG(i: Int) {
        with(binding) {
            ahorcado.setImageDrawable(getDrawable(i))
        }

    }

    fun writeLetter(view: View) {
        var letter = view.contentDescription[0]
        ahorcado.intentarLetra(letter)
        changeIMG(imagenes[ahorcado.intentosRestantes])
        changeText(ahorcado.dibujarPalabraAdivinada())
        view.isEnabled = false
        addLetterOnClick(view)
        if (ahorcado.estaCompleto()) {
            checkResult(ahorcado.resultado())
        }
    }

    fun checkResult(result: Boolean) {
        if (result) {
            return messageDialog("Enhorabuena $namePlayer haz ganado!! \nLa palabra secreta es: \n${ahorcado.getWordSecret()}")
        }
        messageDialog("Lo sentimos $namePlayer ha perdido!! \nLa palabra secreta era: \n${ahorcado.getWordSecret()}")
    }

    fun messageDialog(message: String) {
        MaterialAlertDialogBuilder(this)
            .setTitle(resources.getString(R.string.title))
            .setMessage(message)
            .setNegativeButton(resources.getString(R.string.salir)) { dialog, which ->
                finish()
            }
            .setPositiveButton(resources.getString(R.string.reintentar)) { dialog, which ->
                reloadGame()
            }
            .show()
    }

    fun changeText(text: String) {
        with(binding) {
            palabraSecreta.text = text
        }
    }

    fun addLetterOnClick(view: View) = btnPressList.add(view)

    fun changeStateButton() = btnPressList.forEach { it.isEnabled = true }
}