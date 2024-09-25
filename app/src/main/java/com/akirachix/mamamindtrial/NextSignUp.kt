package com.akirachix.mamamindtrial

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.akirachix.mamamindtrial.databinding.ActivityNextSignUpBinding

class NextSignUp : AppCompatActivity() {
    lateinit var binding: ActivityNextSignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityNextSignUpBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()

        setContentView(binding.root)
        binding.buttonNext.setOnClickListener {
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }


    }
    fun validateLogin(){
        var formErr = false
        clearErrors()
        val username = binding.editTextUsername.text.toString()
        if (username.isBlank()) {
            formErr = true
            binding.editTextUsername.error = "Username is required"
        }

        val email = binding.editTextEmail.text.toString()
        if (email.isBlank()) {
            formErr = true
            binding.editTextEmail.error = "Email is required"
        }

        val phone = binding.editTextPhoneNumber.text.toString()
        if (phone.isBlank()) {
            formErr = true
            binding.editTextPhoneNumber.error = "Phone Number Required is required"
        }
    }

    fun clearErrors(){
        binding.editTextEmail.error = null
    }
    }
