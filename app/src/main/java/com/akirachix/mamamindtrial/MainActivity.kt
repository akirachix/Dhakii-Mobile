package com.akirachix.mamamindtrial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.akirachix.mamamindtrial.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragments = listOf(
//            MentalHealthFragment(),
//            NutritionFragment(),
//            SleepFragment(),
            ExercisesFragment()
        )

        val adapter = ViewPagerAdapter(this, fragments)
        binding.viewPager.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Mental Health"
                1 -> "Nutrition"
                2 -> "Sleep"
                3 -> "Exercises"
                else -> null
            }
        }.attach()

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
//                R.id.navigation_home -> binding.viewPager.currentItem = 0
//                R.id.navigation_care_guide -> binding.viewPager.currentItem = 1
//                R.id.navigation_mothers -> binding.viewPager.currentItem = 2
//                R.id.navigation_profile -> binding.viewPager.currentItem = 3
            }
            true
        }
    }
}