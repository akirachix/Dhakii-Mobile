package com.akirachix.mamamindtrial.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.akirachix.mamamindtrial.QuestionsAdapter
import com.akirachix.mamamindtrial.R
import com.akirachix.mamamindtrial.Successfull_submission
import com.akirachix.mamamindtrial.databinding.ActivityQuestionsBinding
import com.akirachix.mamamindtrial.network.Question
import com.akirachix.mamamindtrial.network.RetrofitClient
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

        questionAdapter = QuestionsAdapter(listOf()) { updatedScore ->
            updateScoreView(updatedScore)
        }

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

        // Prepare the result to send back to the previous activity
        val resultIntent = Intent()

        // Assuming the mother's name or ID was passed to this activity, get it from the intent
        val motherName = intent.getStringExtra("mother_name") ?: "Unknown Mother" // Adjust accordingly

        resultIntent.putExtra("mother_name", motherName)

        // Set the result to OK and pass back the mother's name
        setResult(Activity.RESULT_OK, resultIntent)

        // Finish the Questions activity and return to the previous activity
        finish()

        // Optionally navigate to the success page after finishing the Questions activity
        val successIntent = Intent(this, Successfull_submission::class.java)
        startActivity(successIntent)
    }

    private fun updateScoreView(totalScore: Int) {
        // Find the score TextView in the upper section and update it
        val scoreTextView: TextView = findViewById(R.id.countScore) // Replace with actual ID
        scoreTextView.text = totalScore.toString()
    }
}