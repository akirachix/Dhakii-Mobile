package com.akirachix.mamamindtrial

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.akirachix.mamamindtrial.databinding.ActivitySignupBinding

class SignUp : AppCompatActivity() {

    lateinit var  binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySignupBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()

        setContentView(binding.root)
        binding.btnSignUp.setOnClickListener{
            val intent = Intent(this,HomePage::class.java)
            startActivity(intent)


            binding.btnSignUp.setOnClickListener{
                validateLogin()
            }
        }
        binding.backButton.setOnClickListener{
            val intent = Intent(this,NextSignUp::class.java)
            startActivity(intent)
        }

    }
    fun validateLogin(){
        var formErr = false
        clearErrors()
        val password = binding.editTextPassword.text.toString()
        if  (password .isBlank()) {
            formErr = true
            binding.editTextPassword.error = "Password  is required"
        }

        val confirmPassword = binding.editTextConfirmPassword.text.toString()
        if  ( confirmPassword .isBlank()) {
            formErr = true
            binding.editTextPassword.error = " Confrim Password  is required"
        }

        val loaction = binding.editTextSubLocation.text.toString()
        if (loaction.isBlank()) {
            formErr = true
            binding.editTextSubLocation.error = " SubLocation is required"
        }
    }

    fun clearErrors(){
        binding.editTextPassword.error = null
    }
}
