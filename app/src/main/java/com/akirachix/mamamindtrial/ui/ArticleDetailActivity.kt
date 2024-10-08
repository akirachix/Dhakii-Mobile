package com.akirachix.mamamindtrial.ui
import CareGuideArticle
import android.os.Bundle
import android.text.Html
import androidx.appcompat.app.AppCompatActivity
import com.akirachix.mamamindtrial.databinding.ActivityArticleDetailBinding


class ArticleDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityArticleDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()

        binding = ActivityArticleDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val article = intent.getParcelableExtra<CareGuideArticle>("article")
        article?.let {
            binding.titleTextView.text = it.title
            binding.authorTextView.text = it.author
            binding.subtitleTextView.text = it.subtitle
            binding.contentTextView.text = Html.fromHtml(it.content, Html.FROM_HTML_MODE_LEGACY)
            binding.imageView.setImageResource(it.image)
        }
    }
}

