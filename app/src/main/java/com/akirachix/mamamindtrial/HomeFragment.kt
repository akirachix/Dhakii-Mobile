package com.akirachix.mamamindtrial

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.akirachix.mamamindtrial.api.ApiService
import com.akirachix.mamamindtrial.api.ChpDetail
import com.akirachix.mamamindtrial.api.MotherDetail
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class
HomeFragment : Fragment() {
    private lateinit var apiService: ApiService
    private lateinit var assignedMothersCountTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("https://mamamind-db02af72f48f.herokuapp.com/") // Your base URL
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(ApiService::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        assignedMothersCountTextView = view.findViewById(R.id.assignedMothersCountTextView)

        // Fetch data
        fetchChpsAndMothers()

        return view
    }

    private fun fetchChpsAndMothers() {
        // Fetch the list of mothers
        apiService.getMothersList().enqueue(object : Callback<List<MotherDetail>> {
            override fun onResponse(
                call: Call<List<MotherDetail>>,
                response: Response<List<MotherDetail>>
            ) {
                if (response.isSuccessful) {
                    val mothers = response.body() ?: emptyList()
                    fetchChps(mothers)
                }
            }

            override fun onFailure(call: Call<List<MotherDetail>>, t: Throwable) {

            }
        })
    }

    private fun fetchChps(mothers: List<MotherDetail>) {
        // Fetch the list of CHPs
        apiService.getChps().enqueue(object : Callback<List<ChpDetail>> {
            override fun onResponse(
                call: Call<List<ChpDetail>>,
                response: Response<List<ChpDetail>>
            ) {
                if (response.isSuccessful) {
                    val chps = response.body() ?: emptyList()
                    val assignedMothersCount = countAssignedMothers(mothers, chps)
                    assignedMothersCountTextView.text = assignedMothersCount.toString()
                }
            }

            override fun onFailure(call: Call<List<ChpDetail>>, t: Throwable) {
                // Handle failure
            }
        })
    }

    private fun countAssignedMothers(mothers: List<MotherDetail>, chps: List<ChpDetail>): Int {
        val assignedMotherIds = mutableSetOf<Int>()

        // Log the unique villages
        val motherVillages = mothers.map { it.village }.distinct()
        val chpVillages = chps.map { it.village }.distinct()
        Log.d("HomeFragment", "Unique Mother Villages: $motherVillages")
        Log.d("HomeFragment", "Unique CHP Villages: $chpVillages")

        // Iterate over each CHP
        chps.forEach { chp ->
            mothers.forEach { mother ->
                // Log for checking which villages are matching
                if (mother.village == chp.village) {
                    assignedMotherIds.add(mother.motherId)
                    Log.d("HomeFragment", "Matching: Mother ID ${mother.motherId} from ${mother.village} with CHP from ${chp.village}")
                }
            }
        }

        // Log the final count of assigned mothers
        Log.d("HomeFragment", "Assigned Mothers Count: ${assignedMotherIds.size}")

        return assignedMotherIds.size
    }

}






