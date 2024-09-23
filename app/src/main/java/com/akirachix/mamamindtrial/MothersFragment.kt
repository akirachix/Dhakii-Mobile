package com.akirachix.mamamindtrial

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.akirachix.mamamindtrial.api.MotherDetail
import com.akirachix.mamamindtrial.databinding.FragmentMothersBinding
import com.google.android.material.tabs.TabLayoutMediator
import com.akirachix.mamamindtrial.ui.MissedVisitFragment
import com.akirachix.mamamindtrial.ui.DueVisitFragment
import com.akirachix.mamamindtrial.ui.MothersAdapter
import com.akirachix.mamamindtrial.ui.VisitedFragment

class MothersFragment : Fragment() {

    private lateinit var binding: FragmentMothersBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMothersBinding.inflate(inflater, container, false)



        val adapter = MothersPagerAdapter(this)
        binding.viewPager.adapter = adapter



        // Setup TabLayout with ViewPager2
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Missed Visits"
                1 -> "Due Visits"
                2 -> "Visited"
                else -> null
            }
        }.attach()

        return binding.root
    }

    private inner class MothersPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
        override fun getItemCount(): Int = 3

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> MissedVisitFragment()
                1 -> DueVisitFragment()
                2 -> VisitedFragment()
                else -> Fragment() // Fallback case, shouldn't happen
            }
        }
    }
}