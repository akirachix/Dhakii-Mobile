package com.akirachix.mamamindtrial;


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.akirachix.mamamindtrial.databinding.ActivityMainBinding
import com.postman.mamamind.MothersAdapter


class MainActivity : AppCompatActivity() {

    // Declare a binding variable
    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize the View Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set a LayoutManager to position the items in the RecyclerView
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        // Initialize a large scrollable list of mothers
        val mothersList = List(50) { i -> "Bridget Kathure" }

        // Set the RecyclerView Adapter
        binding.recyclerView.adapter = MothersAdapter(mothersList)

        binding.dueVisitTab.setOnClickListener {
            // Handle Due Visit tab click
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.missedVisitTab.setOnClickListener {
            // Create an Intent to start SecondActivity
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.visitedTab.setOnClickListener {
            // Create an Intent to start SecondActivity
            val intent = Intent(this, VisitedActivity::class.java)
            startActivity(intent)
            finish()
        }


    }
}
