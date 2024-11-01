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
import com.akirachix.mamamindtrial.api.VisitStatus
import com.akirachix.mamamindtrial.databinding.FragmentVisitedBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VisitedFragment : Fragment() {

    private lateinit var binding: FragmentVisitedBinding
    private lateinit var mothersAdapter: MothersAdapter
    private var visitedMothersList = mutableListOf<MotherDetail>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVisitedBinding.inflate(inflater, container, false)
        setupRecyclerView()
        fetchVisitedMothers()
        return binding.root
    }

    private fun setupRecyclerView() {
        val viewColor = resources.getColor(R.color.green, null)
        mothersAdapter = MothersAdapter(visitedMothersList, viewColor) { }
        binding.visitedRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mothersAdapter
        }
    }

    private fun fetchVisitedMothers() {
        val call = RetrofitClient.apiService.getMothersList()

        call.enqueue(object : Callback<List<MotherDetail>> {
            override fun onResponse(call: Call<List<MotherDetail>>, response: Response<List<MotherDetail>>) {
                if (response.isSuccessful) {
                    visitedMothersList.clear()
                    visitedMothersList.addAll(
                        response.body()?.filter { it.visitStatus == VisitStatus.VISITED } ?: emptyList()
                    )
                    mothersAdapter.notifyDataSetChanged()
                } else {
                    Toast.makeText(context, "Failed to fetch visited mothers", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<MotherDetail>>, t: Throwable) {
                Toast.makeText(context, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }



    companion object {
        private const val MOTHERS_LIST_KEY = "visited_mothers_list"

        fun newInstance(visitedMothers: List<MotherDetail>): VisitedFragment {
            val fragment = VisitedFragment()
            val args = Bundle()
            args.putParcelableArrayList(MOTHERS_LIST_KEY, ArrayList(visitedMothers))
            fragment.arguments = args
            return fragment
        }
    }
}