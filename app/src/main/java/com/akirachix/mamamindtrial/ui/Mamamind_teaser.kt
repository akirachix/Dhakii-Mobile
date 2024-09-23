package com.akirachix.mamamindtrial.ui


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.akirachix.mamamindtrial.databinding.ActivityMamamindTeaserBinding

class Mamamind_teaser : AppCompatActivity() {

    lateinit var binding: ActivityMamamindTeaserBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        supportActionBar?.hide()
        binding =ActivityMamamindTeaserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.nextbutton3.setOnClickListener {
            val intent = Intent(this, MamamindLogin::class.java)
            startActivity(intent)
        }

        binding.button4.setOnClickListener {
            val intent =Intent(this, MamamindLogin::class.java)
            startActivity(intent)
        }

    }
}