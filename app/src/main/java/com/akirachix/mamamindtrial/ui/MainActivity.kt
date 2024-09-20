package com.akirachix.mamamindtrial.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.akirachix.mamamindtrial.databinding.ActivityMamamindBinding

class MainActivity : AppCompatActivity() {


    lateinit var  binding: ActivityMamamindBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()

        binding = ActivityMamamindBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.getstartedbutton.setOnClickListener {
            val intent= Intent(this, Mammind_M::class.java)
                     startActivity(intent)
        }

    }
}