package com.akirachix.mamamindtrial.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.akirachix.mamamindtrial.HomePage
import com.akirachix.mamamindtrial.R
import com.akirachix.mamamindtrial.databinding.ActivityLoginBinding
import com.akirachix.mamamindtrial.models.LoginRequest
import com.akirachix.mamamindtrial.models.LoginResponse
import com.akirachix.mamamindtrial.viewmodel.LoginViewModel
import com.akirachix.mamamindtrial.utils.Constants


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        // Redirect user if already logged in
        redirectUser()

        // Set up login button click listener
        binding.btnLogin.setOnClickListener { validateLogin() }

        // Observe login error messages
        loginViewModel.errorLiveData.observe(this) { error ->
            Toast.makeText(this, error, Toast.LENGTH_LONG).show()
        }

        // Observe successful login responses
        loginViewModel.loginLiveData.observe(this) { loginResponse ->
            Toast.makeText(this, "Login Successful", Toast.LENGTH_LONG).show()
            persistLogin(loginResponse)
            startActivity(Intent(this, HomePage::class.java)) // Redirect to MainActivity after login
            finish()
        }
    }

    // Redirect user if already logged in
    private fun redirectUser() {
        val sharedPreferences: SharedPreferences = getSharedPreferences(Constants.PREFS, Context.MODE_PRIVATE)
        val token = sharedPreferences.getString(Constants.ACCESS_TOKEN, "")
        if (!token.isNullOrBlank()) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    // Save login data after successful login
    private fun persistLogin(loginResponse: LoginResponse) {
        val sharedPreferences: SharedPreferences = getSharedPreferences(Constants.PREFS, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(Constants.FIRST_NAME, loginResponse.firstname)
        editor.putString(Constants.LAST_NAME, loginResponse.lastname)
        editor.putString(Constants.ACCESS_TOKEN, loginResponse.accessToken)
        editor.apply()
    }

    // Validate login inputs
    private fun validateLogin() {
        val email = binding.etemail.text.toString()
        val password = binding.etPassword.text.toString()

        if (email.isBlank()) {
            binding.tilEmail.error = "Email Required"
            return
        } else {
            binding.tilEmail.error = null
        }

        if (password.isBlank()) {
            binding.tilPassword.error = "Password Required"
            return
        } else {
            binding.tilPassword.error = null
        }

        // Proceed with login attempt if inputs are valid
        loginViewModel.loginUser(email, password)
    }
}