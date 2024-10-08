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
import com.akirachix.mamamindtrial.databinding.FragmentDueVisitBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DueVisitFragment : Fragment() {

    private lateinit var binding: FragmentDueVisitBinding
    private lateinit var mothersAdapter: MothersAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDueVisitBinding.inflate(inflater, container, false)
        setupRecyclerView()
        fetchDueMothers()
        return binding.root
    }

    private fun setupRecyclerView() {
        // Use yellow color for Due Visit
        val viewColor = resources.getColor(R.color.blue, null)
        mothersAdapter = MothersAdapter(mutableListOf(), viewColor) {

        }
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mothersAdapter
        }
    }

    private fun fetchDueMothers() {
        val call = RetrofitClient.apiService.getMothersList()
        call.enqueue(object : Callback<List<MotherDetail>> {
            override fun onResponse(
                call: Call<List<MotherDetail>>,
                response: Response<List<MotherDetail>>
            ) {
                if (response.isSuccessful) {
                    val mothers = response.body() ?: emptyList()

                    // Pass the entire list of MotherDetail objects directly to the adapter
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
        private const val MOTHERS_LIST_KEY = "due_visit_mothers_list" // Define the key here
        fun newInstance(dueVisitMothers: List<MotherDetail>): DueVisitFragment {
            val fragment = DueVisitFragment()
            val args = Bundle()
            args.putParcelableArrayList(
                MOTHERS_LIST_KEY,
                ArrayList(dueVisitMothers)
            ) // Passing as ArrayList
            fragment.arguments = args
            return fragment
        }
    }
}
