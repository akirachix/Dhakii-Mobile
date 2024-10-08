package com.akirachix.mamamindtrial

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.akirachix.mamamindtrial.api.MotherDetail
import com.akirachix.mamamindtrial.api.VisitStatus
import com.akirachix.mamamindtrial.databinding.FragmentMothersBinding
import com.akirachix.mamamindtrial.ui.DueVisitFragment
import com.akirachix.mamamindtrial.ui.MissedVisitFragment
import com.akirachix.mamamindtrial.ui.MothersAdapter
import com.akirachix.mamamindtrial.ui.Questions
import com.akirachix.mamamindtrial.ui.VisitedFragment
import com.google.android.material.tabs.TabLayoutMediator
import java.util.Date

class MothersFragment : Fragment() {

    private lateinit var binding: FragmentMothersBinding
    private var dueVisitMothersList = mutableListOf<MotherDetail>()
    private var missedVisitMothersList = mutableListOf<MotherDetail>()
    private var visitedMothersList = mutableListOf<MotherDetail>()

    private lateinit var dueVisitAdapter: MothersAdapter
    private lateinit var visitedAdapter: MothersAdapter
    private lateinit var missedVisitAdapter: MothersAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMothersBinding.inflate(inflater, container, false)

        // Initialize adapters for Due Visit and Visited lists
        dueVisitAdapter = MothersAdapter(dueVisitMothersList, R.color.blue) { mother ->
            openQuestionsActivity(mother)
        }
        visitedAdapter = MothersAdapter(visitedMothersList, R.color.green) { mother ->
            // Do nothing for visited mothers (button should not be clickable)
        }
        missedVisitAdapter = MothersAdapter(missedVisitMothersList, R.color.red) { mother ->
            // Handle missed visit logic if needed
        }

        // Setup TabLayout with ViewPager2
        val adapter = MothersPagerAdapter(this)
        binding.viewPager.adapter = adapter

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

    // Function to open the Questions Activity
    private fun openQuestionsActivity(mother: MotherDetail) {
        val intent = Intent(requireContext(), Questions::class.java)
        intent.putExtra("mother_name", mother.firstName)
        startActivityForResult(intent, MothersAdapter.REQUEST_CODE_QUESTIONS)
    }

    // Handle the result from QuestionsActivity when a mother is visited
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == MothersAdapter.REQUEST_CODE_QUESTIONS && resultCode == Activity.RESULT_OK) {
            val visitedMotherName = data?.getStringExtra("mother_name")
            visitedMotherName?.let {
                moveMotherToVisited(it)
            }
        }
    }

    // Move the mother from the Due Visit list to the Visited list
    private fun moveMotherToVisited(motherName: String) {
        val mother = dueVisitMothersList.find { "${it.firstName} ${it.lastName}" == motherName }
        if (mother != null) {
            dueVisitMothersList.remove(mother)
            dueVisitAdapter.notifyDataSetChanged() // Update Due Visit UI

            mother.lastVisitDate = Date() // Set the visit date
            visitedMothersList.add(mother)
            visitedAdapter.notifyDataSetChanged() // Update Visited UI
        }
    }

    // Load and categorize mothers into Due, Missed, and Visited lists
    private fun loadAndCategorizeMothers() {
        val mothersList: List<MotherDetail> = getMothersFromBackend() // Simulate fetching from backend

        mothersList.forEach { mother ->
            when (updateVisitStatus(mother)) {
                VisitStatus.DUE_VISIT -> dueVisitMothersList.add(mother)
                VisitStatus.MISSED_VISIT -> missedVisitMothersList.add(mother)
                VisitStatus.VISITED -> visitedMothersList.add(mother)
            }
        }

        // Notify adapters to update their respective lists
        dueVisitAdapter.notifyDataSetChanged()
        visitedAdapter.notifyDataSetChanged()
        missedVisitAdapter.notifyDataSetChanged()
    }

    // Example logic to update the visit status based on time and last visit date
    private fun updateVisitStatus(mother: MotherDetail): VisitStatus {
        val currentDate = Date()
        val twoWeeksInMillis = 14 * 24 * 60 * 60 * 1000L // 14 days in milliseconds

        if (mother.lastVisitDate != null && currentDate.before(mother.dueDate)) {
            return VisitStatus.VISITED
        }
        if (currentDate.time - mother.dueDate.time > twoWeeksInMillis) {
            return VisitStatus.MISSED_VISIT
        }
        return VisitStatus.DUE_VISIT
    }

    // Periodically check if 14 days have passed and move back to Due Visit list
    fun checkForDueMothers() {
        val currentTime = System.currentTimeMillis()
        val mothersToMoveBack = visitedMothersList.filter { mother ->
            mother.lastVisitDate != null && currentTime - mother.lastVisitDate!!.time >= 14 * 24 * 60 * 60 * 1000L
        }

        // Move them back to Due Visit list
        mothersToMoveBack.forEach { mother ->
            visitedMothersList.remove(mother)
            dueVisitMothersList.add(mother)
        }

        // Notify adapters to update UI
        dueVisitAdapter.notifyDataSetChanged()
        visitedAdapter.notifyDataSetChanged()
    }

    private inner class MothersPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

        override fun getItemCount(): Int {
            return 3 // Number of tabs
        }

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> MissedVisitFragment.newInstance(missedVisitMothersList)
                1 -> DueVisitFragment.newInstance(dueVisitMothersList)
                2 -> VisitedFragment.newInstance(visitedMothersList)
                else -> Fragment() // Fallback case, shouldn't happen
            }
        }
    }

    private fun getMothersFromBackend(): List<MotherDetail> {
        // Simulated list of mothers for the example
        return listOf(
            MotherDetail(1, "Jane", "Doe", "1990-01-01", 2, "2023-01-01", "123456789", "Married", "SubLocation", "Village", Date(), Date(), VisitStatus.DUE_VISIT),
            // Add more mothers here
        )
    }


}