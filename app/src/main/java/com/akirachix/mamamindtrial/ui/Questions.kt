package com.akirachix.mamamindtrial.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.akirachix.mamamindtrial.network.Question
import com.akirachix.mamamindtrial.network.RetrofitClient
import com.akirachix.mamamindtrial.R
import com.akirachix.mamamindtrial.QuestionsAdapter
import com.akirachix.mamamindtrial.Successfull_submission
import com.akirachix.mamamindtrial.api.MotherDetail
import com.akirachix.mamamindtrial.databinding.ActivityQuestionsBinding
import com.akirachix.mamamindtrial.databinding.ActivitySuccessfullSubmissionBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Questions  : AppCompatActivity() {
    private lateinit var binding: ActivityQuestionsBinding
    private lateinit var questionAdapter: QuestionsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        supportActionBar?.hide()

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        questionAdapter = QuestionsAdapter(listOf())
        binding.recyclerView.adapter = questionAdapter

        binding.btnSubmit.setOnClickListener {
            handleSubmit()
        }

        loadQuestions()
    }

    private fun loadQuestions() {
        val apiService = RetrofitClient.getInstance()
        apiService.getQuestions().enqueue(object : Callback<List<Question>> {
            override fun onResponse(call: Call<List<Question>>, response: Response<List<Question>>) {
                if (response.isSuccessful && response.body() != null) {
                    val questionList = response.body()!!
                    questionAdapter.updateQuestions(questionList)
                } else {
                    Log.e("QuestionsActivity", "Error: ${response.errorBody()?.string()}")
                    Toast.makeText(this@Questions, "Failed to load questions", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Question>>, t: Throwable) {
                Log.e("QuestionsActivity", "Failure: ${t.message}")
                Toast.makeText(this@Questions, "Network error", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun handleSubmit() {
        val totalScore = questionAdapter.getTotalScore()
        Toast.makeText(this, "Total Score: $totalScore", Toast.LENGTH_SHORT).show()

        val intent = Intent(this, Successfull_submission::class.java)
        startActivity(intent)
    }
}