package com.akirachix.mamamindtrial

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.akirachix.mamamindtrial.api.ApiService
import com.akirachix.mamamindtrial.models.CHP
import com.akirachix.mamamindtrial.models.CHPAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ChpProfileFragment : Fragment() {

     lateinit var recyclerView: RecyclerView
    lateinit var chpAdapter: CHPAdapter
   lateinit var apiService: ApiService

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(activity)

        // Initialize Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("https://mamamind-db02af72f48f.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(ApiService::class.java)

        // Fetch CHP data
        fetchCHPs()

        return view
    }

    private fun fetchCHPs() {
        val call = apiService.getChps()
        call.enqueue(object : Callback<List<CHP>> {
            override fun onResponse(call: Call<List<CHP>>, response: Response<List<CHP>>) {
                if (response.isSuccessful) {
                    val chps = response.body()
                    chps?.let {
                        chpAdapter = CHPAdapter(it)
                        recyclerView.adapter = chpAdapter
                    }
                }
            }

            override fun onFailure(call: Call<List<CHP>>, t: Throwable) {
                Log.e("API_ERROR", "Failed to fetch data: ${t.message}")
            }
        })
    }
}

