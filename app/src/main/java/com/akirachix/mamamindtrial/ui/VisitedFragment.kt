package com.akirachix.mamamindtrial.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.akirachix.mamamindtrial.R
import com.akirachix.mamamindtrial.api.MotherDetail
import com.akirachix.mamamindtrial.api.RetrofitClient
import com.akirachix.mamamindtrial.databinding.FragmentVisitedBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VisitedFragment : Fragment() {

    private lateinit var binding: FragmentVisitedBinding
    private lateinit var mothersAdapter: MothersAdapter
    private var visitedMothersList = mutableListOf<String>() // Define the visited mothers list
    private lateinit var visitedMothersAdapter: MothersAdapter // Define the adapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVisitedBinding.inflate(inflater, container, false)

        setupRecyclerView()
        fetchVisitedMothers()
        return binding.root
    }

    private fun setupRecyclerView() {
        // Use green color for Visited
        val viewColor = resources.getColor(R.color.green, null)
        mothersAdapter = MothersAdapter(mutableListOf(), viewColor) {

        }
        binding.visitedRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mothersAdapter
        }
    }

    private fun fetchVisitedMothers() {
        val call = RetrofitClient.apiService.getMothersList()
        call.enqueue(object : Callback<List<MotherDetail>> {
            override fun onResponse(
                call: Call<List<MotherDetail>>,
                response: Response<List<MotherDetail>>
            ) {
                if (response.isSuccessful) {
                    val mothers = response.body() ?: emptyList()

                    // Pass the entire list of MotherDetail objects directly
                    mothersAdapter.updateMothers(mothers.filter { it.firstName.isNotEmpty() })
                } else {
                    Toast.makeText(context, "Failed to fetch data", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<MotherDetail>>, t: Throwable) {
                Toast.makeText(context, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }


    companion object {
        private const val MOTHERS_LIST_KEY = "visited_mothers_list" // Define the key here

        fun newInstance(visitedMothers: List<MotherDetail>): VisitedFragment {
            val fragment = VisitedFragment()
            val args = Bundle()
            args.putParcelableArrayList(
                MOTHERS_LIST_KEY,
                ArrayList(visitedMothers)
            ) // Passing as ArrayList
            fragment.arguments = args
            return fragment
        }
    }
    fun addMotherToVisited(motherName: String) {
        visitedMothersList.add(motherName)
        visitedMothersAdapter.notifyDataSetChanged() // Notify the adapter to refresh the data
    }
}