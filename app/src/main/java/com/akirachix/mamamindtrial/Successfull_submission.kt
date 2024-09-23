package com.akirachix.mamamindtrial

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.akirachix.mamamindtrial.databinding.ActivitySuccessfullSubmissionBinding
import com.akirachix.mamamindtrial.ui.Questions

class Successfull_submission : AppCompatActivity() {

    private lateinit var binding: ActivitySuccessfullSubmissionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuccessfullSubmissionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()


        binding.backaroww.setOnClickListener{
            val intent= Intent(this, HomePage::class.java)
            startActivity(intent)
            finish()
        }
        }



    }



