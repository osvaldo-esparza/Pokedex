package com.example.pruebakotlin

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.pruebakotlin.databinding.ActivityRegisterBinding
import com.example.pruebakotlin.ui.view.MainActivity
import com.example.pruebakotlin.ui.viewmodel.RegisterNewUserViewModel
import com.google.android.material.snackbar.Snackbar

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.ktx.Firebase


class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private val registerViewModel : RegisterNewUserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        registerViewModel.registerUser.observe(this, Observer {
            if(it.isSuccessful && it.isComplete){
                val intent = Intent(this, Home::class.java)
                startActivity(intent)
            }
            else{
                Snackbar.make(binding.emailR,"Error: ${it.exception?.message?:""}",Snackbar.LENGTH_SHORT)
                    .setTextColor(Color.WHITE)
                    .setBackgroundTint(Color.RED)
                    .show()
            }
        })

        binding.backLogin.setOnClickListener{
            val intent:Intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    override fun onBackPressed() {
        super.onBackPressedDispatcher.onBackPressed()
        val intent:Intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }




}
