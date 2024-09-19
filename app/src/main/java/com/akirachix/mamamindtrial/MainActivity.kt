package com.akirachix.mamamindtrial

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.akirachix.mamamindtrial.databinding.ActivityMamamindBinding

class MainActivity : AppCompatActivity() {


    lateinit var  binding: ActivityMamamindBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()

        binding = ActivityMamamindBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.getstartedbutton.setOnClickListener {
            val intent= Intent(this, Mammind_m::class.java)
                     startActivity(intent)
        }

    }
}