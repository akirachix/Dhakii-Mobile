package com.akirachix.mamamindtrial

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.SearchView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.akirachix.mamamindtrial.api.MotherDetail
import com.akirachix.mamamindtrial.api.RetrofitClient
import com.akirachix.mamamindtrial.api.VisitStatus
import com.akirachix.mamamindtrial.databinding.FragmentMothersBinding
import com.akirachix.mamamindtrial.ui.DueVisitFragment
import com.akirachix.mamamindtrial.ui.MissedVisitFragment
import com.akirachix.mamamindtrial.ui.MothersAdapter
import com.akirachix.mamamindtrial.ui.MothersAdapter.Companion.REQUEST_CODE_QUESTIONS
import com.akirachix.mamamindtrial.ui.Questions
import com.akirachix.mamamindtrial.ui.VisitedFragment
import com.google.android.material.tabs.TabLayoutMediator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Date

class MothersFragment : Fragment() {

    private lateinit var binding: FragmentMothersBinding
    private var originalDueVisitMothersList = mutableListOf<MotherDetail>()
    private var dueVisitMothersList = mutableListOf<MotherDetail>()
    private var missedVisitMothersList = mutableListOf<MotherDetail>()
    private var visitedMothersList = mutableListOf<MotherDetail>()

    private lateinit var dueVisitAdapter: MothersAdapter
    private lateinit var visitedAdapter: MothersAdapter
    private lateinit var missedVisitAdapter: MothersAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMothersBinding.inflate(inflater, container, false)

        // Initialize adapters
        dueVisitAdapter = MothersAdapter(dueVisitMothersList, R.color.blue) { mother ->
            openQuestionsActivity(mother)
        }
        visitedAdapter = MothersAdapter(visitedMothersList, R.color.green) { }
        missedVisitAdapter = MothersAdapter(missedVisitMothersList, R.color.red) { mother ->
            openQuestionsActivity(mother)
        }

        // Setup ViewPager and Tabs
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

        setupSearchView()
        loadMothersFromBackend()

        return binding.root
    }

    private fun setupSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterMothersList(newText)
                return true
            }
        })
    }

    private fun filterMothersList(query: String?) {
        val searchText = query?.lowercase() ?: ""
        dueVisitMothersList.clear()

        if (searchText.isEmpty()) {
            dueVisitMothersList.addAll(originalDueVisitMothersList)
        } else {
            dueVisitMothersList.addAll(originalDueVisitMothersList.filter {
                it.firstName.lowercase().contains(searchText) || it.lastName.lowercase().contains(searchText)
            })
        }

        dueVisitAdapter.notifyDataSetChanged()
    }

    private fun openQuestionsActivity(mother: MotherDetail) {
        val intent = Intent(requireContext(), Questions::class.java)
        intent.putExtra("mother_name", mother.firstName)
        startActivityForResult(intent, REQUEST_CODE_QUESTIONS)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_QUESTIONS && resultCode == Activity.RESULT_OK) {
            val visitedMotherName = data?.getStringExtra("mother_name")
            visitedMotherName?.let { moveMotherToVisited(it) }
        }
    }

    private fun moveMotherToVisited(motherName: String) {
        val mother = dueVisitMothersList.find { "${it.firstName} ${it.lastName}" == motherName }
        if (mother != null) {
            dueVisitMothersList.remove(mother)
            originalDueVisitMothersList.remove(mother)  // Remove from original list too
            mother.lastVisitDate = Date()
            mother.visitStatus = VisitStatus.VISITED
            visitedMothersList.add(0, mother)
            updateAdapters()
        }
    }

    private fun loadMothersFromBackend() {
        val call = RetrofitClient.apiService.getMothersList()
        call.enqueue(object : Callback<List<MotherDetail>> {
            override fun onResponse(call: Call<List<MotherDetail>>, response: Response<List<MotherDetail>>) {
                if (response.isSuccessful) {
                    val mothersList = response.body()
                        ?.distinctBy { "${it.firstName} ${it.lastName}".lowercase() }
                        ?: emptyList()

                    Log.d("MothersFragment", "Fetched Unique Mothers Count: ${mothersList.size}")
                    categorizeMothers(mothersList)
                } else {
                    Log.e("MothersFragment", "Failed to fetch mothers. Error: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<List<MotherDetail>>, t: Throwable) {
                Log.e("MothersFragment", "Error fetching mothers: ${t.message}")
            }
        })
    }

    private fun categorizeMothers(mothersList: List<MotherDetail>) {
        dueVisitMothersList.clear()
        missedVisitMothersList.clear()
        visitedMothersList.clear()

        val currentTime = Date().time
        val twoWeeksInMillis = 14 * 24 * 60 * 60 * 1000L

        mothersList.forEach { mother ->
            when {
                mother.visitStatus == VisitStatus.MISSED_VISIT -> {
                    missedVisitMothersList.add(mother)
                }
                mother.isVisited && mother.lastVisitDate != null &&
                        currentTime - mother.lastVisitDate!!.time >= twoWeeksInMillis -> {
                    mother.visitStatus = VisitStatus.DUE_VISIT
                    dueVisitMothersList.add(mother)
                }
                mother.isVisited -> {
                    visitedMothersList.add(mother)
                }
                else -> {
                    mother.visitStatus = VisitStatus.DUE_VISIT
                    dueVisitMothersList.add(mother)
                }
            }
        }

        // Make a copy of the original dueVisit list to retain full data for search purposes
        originalDueVisitMothersList.clear()
        originalDueVisitMothersList.addAll(dueVisitMothersList)

        // Sort each list alphabetically by first name
        dueVisitMothersList.sortBy { it.firstName.lowercase() }
        missedVisitMothersList.sortBy { it.firstName.lowercase() }
        visitedMothersList.sortBy { it.firstName.lowercase() }

        Log.d("MothersFragment", "Total Due Visit: ${dueVisitMothersList.size}")
        Log.d("MothersFragment", "Total Missed Visit: ${missedVisitMothersList.size}")
        Log.d("MothersFragment", "Total Visited: ${visitedMothersList.size}")

        updateAdapters()
    }

    private fun updateAdapters() {
        dueVisitAdapter.notifyDataSetChanged()
        visitedAdapter.notifyDataSetChanged()
        missedVisitAdapter.notifyDataSetChanged()
    }

    private inner class MothersPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
        override fun getItemCount() = 3
        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> MissedVisitFragment.newInstance(missedVisitMothersList)
                1 -> DueVisitFragment.newInstance(dueVisitMothersList)
                2 -> VisitedFragment.newInstance(visitedMothersList)
                else -> Fragment()
            }
        }
    }
}