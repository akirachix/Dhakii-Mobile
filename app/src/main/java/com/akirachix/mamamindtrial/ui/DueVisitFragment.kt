package com.akirachix.mamamindtrial.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
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
import com.akirachix.mamamindtrial.databinding.FragmentDueVisitBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DueVisitFragment : Fragment() {

    private lateinit var binding: FragmentDueVisitBinding
    private lateinit var mothersAdapter: MothersAdapter
    private var dueVisitMothersList = mutableListOf<MotherDetail>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDueVisitBinding.inflate(inflater, container, false)
        setupRecyclerView()
        fetchMothersFromBackend()
        return binding.root
    }

    private fun setupRecyclerView() {
        val viewColor = resources.getColor(R.color.blue, null)
        mothersAdapter = MothersAdapter(dueVisitMothersList, viewColor) { mother ->
            openQuestionsActivity(mother)
        }
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mothersAdapter
        }
    }

    private fun fetchMothersFromBackend() {
        val call = RetrofitClient.apiService.getMothersList()
        call.enqueue(object : Callback<List<MotherDetail>> {
            override fun onResponse(
                call: Call<List<MotherDetail>>,
                response: Response<List<MotherDetail>>
            ) {
                if (response.isSuccessful) {
                    val mothers = response.body()?.filter { it.visitStatus == VisitStatus.DUE_VISIT } ?: emptyList()
                    dueVisitMothersList.clear()
                    dueVisitMothersList.addAll(mothers.sortedBy { it.firstName.lowercase() })
                    mothersAdapter.notifyDataSetChanged()
                    Log.d("DueVisitFragment", "Mothers in Due Visit: ${dueVisitMothersList.size}")
                } else {
                    Toast.makeText(context, "Failed to fetch data", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<MotherDetail>>, t: Throwable) {
                Toast.makeText(context, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun openQuestionsActivity(mother: MotherDetail) {
        val intent = Intent(requireContext(), Questions::class.java)
        intent.putExtra("mother_name", mother.firstName)
        startActivityForResult(intent, MothersAdapter.REQUEST_CODE_QUESTIONS)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == MothersAdapter.REQUEST_CODE_QUESTIONS && resultCode == Activity.RESULT_OK) {
            val visitedMotherName = data?.getStringExtra("mother_name")
            visitedMotherName?.let { moveMotherToVisited(it) }
        }
    }

    private fun moveMotherToVisited(motherName: String) {
        val mother = dueVisitMothersList.find { "${it.firstName} ${it.lastName}" == motherName }
        if (mother != null) {
            dueVisitMothersList.remove(mother)
            mother.visitStatus = VisitStatus.VISITED
            mothersAdapter.notifyDataSetChanged()
        }
    }
    companion object {
        private const val MOTHERS_LIST_KEY = "due-visit_mothers_list"

        fun newInstance(missedVisitMothers: List<MotherDetail>): MissedVisitFragment {
            val fragment = MissedVisitFragment()
            val args = Bundle()
            args.putParcelableArrayList(MOTHERS_LIST_KEY, ArrayList(missedVisitMothers))
            fragment.arguments = args
            return fragment
        }
    }
}