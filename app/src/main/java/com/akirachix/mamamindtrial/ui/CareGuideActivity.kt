package com.akirachix.mamamindtrial.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.akirachix.mamamindtrial.CareGuideFragment
import com.akirachix.mamamindtrial.R
import com.akirachix.mamamindtrial.databinding.ActivityCareGuideBinding

class CareGuideActivity : AppCompatActivity() {

    // ViewBinding instance
    private lateinit var binding: ActivityCareGuideBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()

        // Initializing ViewBinding
        binding = ActivityCareGuideBinding.inflate(layoutInflater)

        // Setting the content view to the binding's root view
        setContentView(binding.root)

        // Load the default fragment (CareGuideFragment)
        loadFragment(CareGuideFragment())

//
    }

    // Function to load fragments into the container
    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}
