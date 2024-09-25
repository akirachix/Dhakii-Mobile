package com.akirachix.mamamindtrial

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.akirachix.mamamindtrial.databinding.ActivityHomePageBinding
import com.akirachix.mamamindtrial.ui.MainActivity2

class HomePage : AppCompatActivity() {
    lateinit var binding: ActivityHomePageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomePageBinding.inflate(layoutInflater)
        supportActionBar?.hide()
        setContentView(binding.root)



        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    // Load HomeFragment when Home is selected
                    loadFragment(HomeFragment())
                    true
                }
                R.id.careguide -> {
                    // Load CareGuideFragment when Careguide is selected
                    loadFragment(CareGuideFragment())
                    true
                }
                R.id.mothers -> {
                    // Start MainActivity2 when Mothers is selected
                    val intent = Intent(this, MainActivity2::class.java)
                    startActivity(intent)
                    true
                }
                R.id.profile -> {
                    // Load ProfileFragment when Profile is selected
                    loadFragment(ChpProfileFragment())
                    true
                }
                else -> false
            }
        }

        // Set Home as the default fragment
        if (savedInstanceState == null) {
            loadFragment(HomeFragment())
            binding.bottomNavigationView.selectedItemId = R.id.home
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fcvHome, fragment)
            .commit()
    }
}
