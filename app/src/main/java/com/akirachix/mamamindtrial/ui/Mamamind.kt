package com.akirachix.mamamindtrial.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.akirachix.mamamindtrial.R
import com.akirachix.mamamindtrial.databinding.ActivityMamamindBinding

class MainActivity : AppCompatActivity() {


    lateinit var  binding: ActivityMamamindBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()

        binding = ActivityMamamindBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.getstartedbutton.setOnClickListener {
            val intent= Intent(this, Mamamind_teaser::class.java)
            startActivity(intent)
        }

    }
}

