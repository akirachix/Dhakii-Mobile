package com.akirachix.mamamindtrial.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.akirachix.mamamindtrial.R
import com.akirachix.mamamindtrial.api.MotherDetail
import com.akirachix.mamamindtrial.api.RetrofitClient
import com.akirachix.mamamindtrial.api.VisitStatus
import com.akirachix.mamamindtrial.databinding.FragmentMissedVisitBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MissedVisitFragment : Fragment() {

    private lateinit var binding: FragmentMissedVisitBinding
    private lateinit var mothersAdapter: MothersAdapter
    private var missedVisitMothersList = mutableListOf<MotherDetail>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMissedVisitBinding.inflate(inflater, container, false)
        setupRecyclerView()
        fetchMissedMothers()
        return binding.root
    }

    private fun setupRecyclerView() {
        val viewColor = resources.getColor(R.color.red, null)
        mothersAdapter = MothersAdapter(missedVisitMothersList, viewColor) { mother ->
            openQuestionsActivity(mother)
        }

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
                    missedVisitMothersList.clear()
                    missedVisitMothersList.addAll(
                        response.body()?.filter { it.visitStatus == VisitStatus.MISSED_VISIT } ?: emptyList()
                    )
                    mothersAdapter.notifyDataSetChanged()
                } else {
                    Toast.makeText(context, "Failed to fetch missed visits", Toast.LENGTH_SHORT).show()
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
        startActivity(intent)
    }

    companion object {
        private const val MOTHERS_LIST_KEY = "missed_visit_mothers_list"

        fun newInstance(missedVisitMothers: List<MotherDetail>): MissedVisitFragment {
            val fragment = MissedVisitFragment()
            val args = Bundle()
            args.putParcelableArrayList(MOTHERS_LIST_KEY, ArrayList(missedVisitMothers))
            fragment.arguments = args
            return fragment
        }
    }
}