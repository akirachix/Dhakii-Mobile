package com.akirachix.mamamindtrial.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.akirachix.mamamindtrial.CareGuideArticle
import com.akirachix.mamamindtrial.databinding.ActivityArticleDetailBinding
import com.akirachix.mamamindtrial.network.RetrofitClient
import com.bumptech.glide.Glide
import kotlinx.coroutines.launch

class ArticleDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityArticleDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArticleDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val articleId = intent.getIntExtra("article_id", -1)
        if (articleId != -1) {
            loadArticleDetails(articleId)
        } else {
            // Handle error
        }
    }

    private fun loadArticleDetails(articleId: Int) {
        lifecycleScope.launch {
            try {
                val article = RetrofitClient.instance.getArticleDetails(articleId)
                displayArticleDetails(article)
            } catch (e: Exception) {
                // Handle error
            }
        }
    }

    private fun displayArticleDetails(article: CareGuideArticle) {
        binding.titleTextView.text = article.title
        binding.authorTextView.text = article.author
        binding.subtitleTextView.text = article.subtitle
        binding.contentTextView.text = article.content

        Glide.with(this)
            .load(article.image)
            .into(binding.imageView)
    }
}