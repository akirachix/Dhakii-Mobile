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

        // Initializing ViewBinding
        binding = ActivityCareGuideBinding.inflate(layoutInflater)

        // Setting the content view to the binding's root view
        setContentView(binding.root)

        // Load the default fragment (CareGuideFragment)
        loadFragment(CareGuideFragment())

//        // Bottom Navigation listener to switch between fragments
//        binding.bottomNavigationView.setOnItemSelectedListener { item ->
//            var fragment: Fragment? = null
//            when (item.itemId) {
//                R.id.nav_home -> fragment = HomeFragment()
//                R.id.nav_care_guide -> fragment = CareGuideFragment() // Care guide fragment
//                R.id.nav_mothers -> fragment = MothersFragment()
//                R.id.nav_profile -> fragment = ProfileFragment()
//            }
//            if (fragment != null) {
//                loadFragment(fragment)
//            }
//            true
//        }
    }

    // Function to load fragments into the container
    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}
