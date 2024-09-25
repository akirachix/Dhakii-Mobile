package com.akirachix.mamamindtrial

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class CareGuidePagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> CareGuideListFragment.newInstance("Mental Health")
            1 -> CareGuideListFragment.newInstance("Nutrition")
            2 -> CareGuideListFragment.newInstance("Sleep")
            3 -> CareGuideListFragment.newInstance("Exercises")
            else -> throw IllegalArgumentException("Invalid position")
        }
    }
}