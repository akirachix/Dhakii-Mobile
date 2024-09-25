package com.akirachix.mamamindtrial.ui

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.akirachix.mamamindtrial.HomePage
import com.akirachix.mamamindtrial.R
import com.akirachix.mamamindtrial.databinding.ActivityMainBinding

class MainActivity2: AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener{
            val intent= Intent(this, HomePage::class.java)
            startActivity(intent)
            finish()
        }
        supportActionBar?.hide()

        // Initially load the DueVisitFragment
        loadFragment(DueVisitFragment())
        setTabColors(binding.dueVisitTab) // Set initial tab color

        // Set tab click listeners to switch fragments
        binding.dueVisitTab.setOnClickListener {
            loadFragment(DueVisitFragment())
            setTabColors(binding.dueVisitTab)
        }

        binding.missedVisitTab.setOnClickListener {
            loadFragment(MissedVisitFragment())
            setTabColors(binding.missedVisitTab)
        }

        binding.visitedTab.setOnClickListener {
            loadFragment(VisitedFragment())
            setTabColors(binding.visitedTab)
        }



    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, fragment)
        transaction.addToBackStack(null) // Allows back navigation
        transaction.commit()
    }

    private fun setTabColors(selectedTab: TextView) {
        // Reset all tabs to default color
        binding.dueVisitTab.setTextColor(ContextCompat.getColor(this, R.color.black))
        binding.missedVisitTab.setTextColor(ContextCompat.getColor(this, R.color.black))
        binding.visitedTab.setTextColor(ContextCompat.getColor(this, R.color.black))

        // Set selected tab color
        when (selectedTab.id) {
            R.id.dueVisitTab -> selectedTab.setTextColor(
                ContextCompat.getColor(
                    this,
                    R.color.color_for_main_activity
                )
            )

            R.id.missedVisitTab -> selectedTab.setTextColor(
                ContextCompat.getColor(
                    this,
                    R.color.color_for_main_activity
            )
            )

            R.id.visitedTab -> selectedTab.setTextColor(
                ContextCompat.getColor(
                    this,
                    R.color.color_for_main_activity
                )
            )
        }
    }
}

