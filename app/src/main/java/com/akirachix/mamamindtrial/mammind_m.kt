package com.akirachix.mamamindtrial

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.akirachix.mamamindtrial.databinding.ActivityMammindMBinding

class Mammind_m : AppCompatActivity() {
  lateinit var binding: ActivityMammindMBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()

        binding=ActivityMammindMBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.nextbtn.setOnClickListener {
            val intent = Intent(this,Mamamind_teaser::class.java)
            startActivity(intent)
        }
        binding.skipbtn.setOnClickListener {
            val intent =Intent(this, Mamamind_login::class.java)
            startActivity(intent)
        }


    }
}

