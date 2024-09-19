package com.akirachix.mamamindtrial;


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.akirachix.mamamindtrial.databinding.ActivitySecondBinding
import com.postman.mamamind.MothersAdapter


class SecondActivity : AppCompatActivity() {

    // Declare a binding variable
    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize the View Binding
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set a LayoutManager to position the items in the RecyclerView
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        // Initialize a large scrollable list of mothers
        val mothersList = List(50) { i -> "Bridget Kathure" }

        // Set the RecyclerView Adapter
        binding.recyclerView.adapter = MothersAdapter(mothersList)

    }
}