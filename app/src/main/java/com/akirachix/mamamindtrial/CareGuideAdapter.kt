package com.akirachix.mamamindtrial
import CareGuideArticle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.akirachix.mamamindtrial.databinding.ItemCareGuideArticleBinding
import com.bumptech.glide.Glide

class CareGuideAdapter(
    private val articles: List<CareGuideArticle>,
    private val onItemClick: (CareGuideArticle) -> Unit
) : RecyclerView.Adapter<CareGuideAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemCareGuideArticleBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(article: CareGuideArticle) {
            binding.titleTextView.text = article.title
            Glide.with(binding.root.context)
                .load(article.image) // Load drawable resource ID
                .into(binding.imageView)

            binding.root.setOnClickListener { onItemClick(article) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCareGuideArticleBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(articles[position])
    }

    override fun getItemCount(): Int = articles.size
}



