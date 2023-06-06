package com.mayra.juegodelahorcadoMayra

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ollin.juegodelahorcado.databinding.ActivityMainBinding
import com.mayra.juegodelahorcadoMayra.ui.GameActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        goToPlay()
    }

    fun goToPlay() {
        with(binding) {
            btnPlay.setOnClickListener {
                val name: String = namePlayer.text.toString()
                if (name.isEmpty()) {
                    validateName()
                } else {
                    goToGame(name)
                }
            }
        }
    }

    fun validateName() =
        Toast.makeText(this, "Por favor ingresa un nombre!!", Toast.LENGTH_LONG).show()

    fun goToGame(name: String) {
        val intent = Intent(this, GameActivity::class.java)
        intent.putExtra("nameplayer", name)
        startActivity(intent)
        finish()
    }
}