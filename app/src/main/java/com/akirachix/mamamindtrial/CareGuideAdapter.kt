package com.akirachix.mamamindtrial

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.akirachix.mamamindtrial.databinding.ItemCareGuideArticleBinding
import com.bumptech.glide.Glide

class CareGuideAdapter(
    private val articles: List<CareGuideArticle>,
    private val category: String,
    private val onItemClick: (CareGuideArticle) -> Unit
) : RecyclerView.Adapter<CareGuideAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemCareGuideArticleBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(article: CareGuideArticle) {
            binding.titleTextView.text = article.title

            // Manually add an image and subtitle if the API response doesn't have them
            val image = when (category) {
                "Mental Health" -> R.drawable.mental_health_ig
                "Nutrition" -> R.drawable.nutrition_ig
                "Sleep" -> R.drawable.sleep_ig
                "Exercises" -> R.drawable.default_exercise_img
                else -> R.drawable.default_img
            }

            Glide.with(binding.root.context)
                .load(image)
                .into(binding.imageView)

            // Handle click event and pass article category as well
            binding.root.setOnClickListener {
                onItemClick(article)
            }
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



//package com.akirachix.mamamindtrial
//
//
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//import com.akirachix.mamamindtrial.databinding.ItemCareGuideArticleBinding
//import com.bumptech.glide.Glide
//
//
//class CareGuideAdapter(
//    private val articles: List<CareGuideArticle>,
//    private val category: String,
//    private val onItemClick: (CareGuideArticle) -> Unit
//) : RecyclerView.Adapter<CareGuideAdapter.ViewHolder>() {
//
//
//    inner class ViewHolder(private val binding: ItemCareGuideArticleBinding) :
//        RecyclerView.ViewHolder(binding.root) {
//
//
//        fun bind(article: CareGuideArticle) {
//            binding.titleTextView.text = article.title
//
//
//            val image = when (category) {
//                "Mental Health" -> R.drawable.mental_health_img
//                "Nutrition" -> R.drawable.nutrition_img
//                "Sleep" -> R.drawable.sleep_img
//                "Exercises" -> R.drawable.exercise_img
//                else -> R.drawable.default_img
//            }
//
//
//            Glide.with(binding.root.context)
//                .load(image)
//                .into(binding.imageView)
//
//
//            // Handle click event
//            binding.root.setOnClickListener { onItemClick(article) }
//        }
//    }
//
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val binding = ItemCareGuideArticleBinding.inflate(
//            LayoutInflater.from(parent.context),
//            parent,
//            false
//        )
//        return ViewHolder(binding)
//    }
//
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.bind(articles[position])
//    }
//
//
//    override fun getItemCount(): Int = articles.size
//}
//
