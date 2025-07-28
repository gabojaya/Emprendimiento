package com.gabriel.jaya.emprendimiento

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gabriel.jaya.emprendimiento.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Lógica para navegar a la pantalla principal
        binding.btnContinue.setOnClickListener {
            // Aquí iría tu lógica de validación de email
            goToMainActivity()
        }
        binding.btnGoogle.setOnClickListener {
            // Lógica de login con Google
            goToMainActivity()
        }
        binding.btnApple.setOnClickListener {
            // Lógica de login con Google
            goToMainActivity()
        }
    }

    private fun goToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish() // Cierra LoginActivity para que no se pueda volver atrás
    }
}