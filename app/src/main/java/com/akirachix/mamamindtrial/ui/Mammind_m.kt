package com.akirachix.mamamindtrial.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.akirachix.mamamindtrial.databinding.ActivityMammindMBinding

class Mammind_M : AppCompatActivity() {
  lateinit var binding: ActivityMammindMBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()

        binding=ActivityMammindMBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.nextbtn.setOnClickListener {
            val intent = Intent(this, MamamindLogin::class.java)
            startActivity(intent)
        }
        binding.skipbtn.setOnClickListener {
            val intent =Intent(this, Mamamind_teaser::class.java)
            startActivity(intent)
        }



    }
}

