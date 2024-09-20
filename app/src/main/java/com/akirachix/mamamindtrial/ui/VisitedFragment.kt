package com.akirachix.mamamindtrial.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.akirachix.mamamindtrial.api.MotherDetail
import com.akirachix.mamamindtrial.api.RetrofitClient
import com.postman.mamamind.MothersAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.akirachix.mamamindtrial.R
import com.akirachix.mamamindtrial.databinding.FragmentVisitedBinding


class VisitedFragment : Fragment() {

    private lateinit var binding: FragmentVisitedBinding
    private lateinit var mothersAdapter: MothersAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentVisitedBinding.inflate(inflater, container, false)
        setupRecyclerView()
        fetchMissedMothers()
        return binding.root
    }

    private fun setupRecyclerView() {
        // Use red color for Missed Visit
        val viewColor = resources.getColor(R.color.green, null)
        mothersAdapter = MothersAdapter(emptyList(), viewColor)
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mothersAdapter
        }
    }

    private fun fetchMissedMothers() {
        val call = RetrofitClient.apiService.getMothersList()
        call.enqueue(object : Callback<List<MotherDetail>> {
            override fun onResponse(call: Call<List<MotherDetail>>, response: Response<List<MotherDetail>>) {
                if (response.isSuccessful) {
                    val mothers = response.body() ?: emptyList()
                    // Map MotherDetail to String
                    val mothersNames = mothers.map { "${it.firstName} ${it.lastName}" }
                    mothersAdapter.updateMothers(mothersNames.filter { it.isNotEmpty() })
                } else {
                    Toast.makeText(context, "Failed to fetch data", Toast.LENGTH_SHORT).show()
                }
            }


            override fun onFailure(call: Call<List<MotherDetail>>, t: Throwable) {
                Toast.makeText(context, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}

