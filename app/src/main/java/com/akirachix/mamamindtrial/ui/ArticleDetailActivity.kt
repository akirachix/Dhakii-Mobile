package com.akirachix.mamamindtrial.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.akirachix.mamamindtrial.R
import com.akirachix.mamamindtrial.databinding.ActivityArticleDetailBinding
import com.bumptech.glide.Glide

class ArticleDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityArticleDetailBinding
    private var articleCategory: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()

        // Initialize ViewBinding
        binding = ActivityArticleDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val articleId = intent.getIntExtra("article_id", -1)
        articleCategory = intent.getStringExtra("article_category") // Get category from intent

        if (articleId != -1) {
            // Load the article details (you can adjust this logic when the API works)
            displayArticleDetails()
        }
    }

    // Display details with category-specific images
    private fun displayArticleDetails() {
        binding.titleTextView.text = "Manually Set Article Title"
        binding.authorTextView.text = "John Doe"
        binding.subtitleTextView.text = "Subtitle Example"
        binding.contentTextView.text = "Full content of the article..."

        // Load a category-specific default image if there's no image from the API
        val defaultImage = when (articleCategory) {
            "Mental Health" -> R.drawable.default_mental_health_img
            "Nutrition" -> R.drawable.default_nutrition_img
            "Sleep" -> R.drawable.default_sleep_img
            "Exercises" -> R.drawable.exercise_ig
            else -> R.drawable.default_img
        }

        Glide.with(this)
            .load(defaultImage) // Load the default image based on the category
            .into(binding.imageView)
    }
}





//package com.akirachix.mamamindtrial.ui
//
//
//import android.os.Bundle
//import androidx.appcompat.app.AppCompatActivity
//import com.akirachix.mamamindtrial.R
//import com.akirachix.mamamindtrial.databinding.ActivityArticleDetailBinding
//import com.bumptech.glide.Glide
//
//
//class ArticleDetailActivity : AppCompatActivity() {
//
//
//    private lateinit var binding: ActivityArticleDetailBinding
//
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        supportActionBar?.hide()
//
//
//        binding = ActivityArticleDetailBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//
//        val articleId = intent.getIntExtra("article_id", -1)
//        if (articleId != -1) {
//            // Temporarily set manual data for now until the API is working
//            displayArticleDetails()
//        }
//    }
//
//
//    private fun displayArticleDetails() {
//        binding.titleTextView.text = "Manually Set Article Title"
//        binding.authorTextView.text = "John Doe"
//        binding.subtitleTextView.text = "Subtitle Example"
//        binding.contentTextView.text = "Full content of the article..."
//
//
//        Glide.with(this)
//            .load(R.drawable.default_img)
//            .into(binding.imageView)
//    }
//}
//

