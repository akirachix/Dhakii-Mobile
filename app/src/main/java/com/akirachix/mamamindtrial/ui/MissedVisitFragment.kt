package com.akirachix.mamamindtrial.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.akirachix.mamamindtrial.R
import com.akirachix.mamamindtrial.api.MotherDetail
import com.akirachix.mamamindtrial.databinding.FragmentMissedVisitBinding

class MissedVisitFragment : Fragment() {

    private lateinit var binding: FragmentMissedVisitBinding
    private lateinit var mothersAdapter: MothersAdapter
    private var missedVisitMothersList = mutableListOf<MotherDetail>() // List to hold passed data

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMissedVisitBinding.inflate(inflater, container, false)

        // Get the list of missed visit mothers passed via newInstance()
        arguments?.let {
            missedVisitMothersList = it.getParcelableArrayList(MOTHERS_LIST_KEY) ?: mutableListOf()
        }

        setupRecyclerView()

        return binding.root
    }

    private fun setupRecyclerView() {
        // Use red color for Missed Visit
        val viewColor = resources.getColor(R.color.red, null)

        // Create the adapter using the passed list of missed visit mothers
        val mothersNames = missedVisitMothersList.map { "${it.firstName} ${it.lastName}" }.toMutableList()
        mothersAdapter = MothersAdapter(missedVisitMothersList, viewColor){

        }

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mothersAdapter
        }
    }

    // Companion object to help create the fragment and pass data
    companion object {
        private const val MOTHERS_LIST_KEY = "missed_visit_mothers_list"

        // Method to create an instance of the fragment and pass the data
        fun newInstance(missedVisitMothers: List<MotherDetail>): MissedVisitFragment {
            val fragment = MissedVisitFragment()
            val args = Bundle()
            args.putParcelableArrayList(MOTHERS_LIST_KEY, ArrayList(missedVisitMothers)) // Passing as ArrayList
            fragment.arguments = args
            return fragment
        }
    }
}
