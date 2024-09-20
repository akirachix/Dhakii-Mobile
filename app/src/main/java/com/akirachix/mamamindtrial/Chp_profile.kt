package com.akirachix.mamamindtrial

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.akirachix.mamamindtrial.databinding.ActivityChpProfileBinding
import com.akirachix.mamamindtrial.databinding.ActivitySignupBinding

class Chp_profile : AppCompatActivity() {
    lateinit var binding: ActivityChpProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()

        binding =ActivityChpProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backButton.setOnClickListener{
            val intent = Intent(this, HomePage::class.java)
            startActivity(intent)
        }
    }
}