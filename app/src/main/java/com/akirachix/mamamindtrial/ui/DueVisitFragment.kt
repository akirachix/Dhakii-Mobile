package com.akirachix.mamamindtrial.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.akirachix.mamamindtrial.R
import com.akirachix.mamamindtrial.api.RetrofitClient
import com.akirachix.mamamindtrial.databinding.FragmentDueVisitBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.akirachix.mamamindtrial.api.MotherDetail
import com.postman.mamamind.MothersAdapter


class DueVisitFragment : Fragment() {

    private lateinit var binding: FragmentDueVisitBinding
    private lateinit var mothersAdapter: MothersAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentDueVisitBinding.inflate(inflater, container, false)
        setupRecyclerView()
        fetchMothersList()
        return binding.root
    }

    private fun setupRecyclerView() {
        // Use red color for Missed Visit
        val viewColor = resources.getColor(R.color.red, null)
        mothersAdapter = MothersAdapter(emptyList(), viewColor)
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mothersAdapter
        }
    }

    private fun fetchMothersList() {
        RetrofitClient.apiService.getMothersList()
            .enqueue(object : Callback<List<MotherDetail>> {
                override fun onResponse(
                    call: Call<List<MotherDetail>>,
                    response: Response<List<MotherDetail>>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        // Log the complete response for debugging
                        Log.d("API Response", response.body().toString())

                        // Extract the list of mothers
                        val mothersList = response.body()!!.map {
                            "${it.firstName} ${it.lastName}" // Check if these fields are populated
                        }

                        val viewColor = resources.getColor(R.color.color_for_main_activity, null)

                        // Set the adapter with the fetched data
                        binding.recyclerView.adapter = MothersAdapter(mothersList, viewColor)
                    } else {
                        Toast.makeText(requireContext(), "Failed to fetch data", Toast.LENGTH_SHORT)
                            .show()
                    }
                }

                override fun onFailure(call: Call<List<MotherDetail>>, t: Throwable) {
                    Log.e("DueVisitFragment", "API call failed: ${t.message}")
                    Toast.makeText(requireContext(), "Error fetching data", Toast.LENGTH_SHORT)
                        .show()
                }
            })
    }
}



