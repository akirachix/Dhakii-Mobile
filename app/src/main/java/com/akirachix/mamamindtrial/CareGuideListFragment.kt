package com.akirachix.mamamindtrial

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.akirachix.mamamindtrial.databinding.FragmentCareGuideListBinding
import com.akirachix.mamamindtrial.network.RetrofitClient
import com.akirachix.mamamindtrial.ui.ArticleDetailActivity
import kotlinx.coroutines.launch

class CareGuideListFragment : Fragment() {

    private var _binding: FragmentCareGuideListBinding? = null
    private val binding get() = _binding!!

    private lateinit var category: String

    companion object {
        private const val ARG_CATEGORY = "category"

        fun newInstance(category: String): CareGuideListFragment {
            return CareGuideListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_CATEGORY, category)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            category = it.getString(ARG_CATEGORY) ?: ""
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCareGuideListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.layoutManager = LinearLayoutManager(context)

        binding.swipeRefreshLayout.setOnRefreshListener {
            fetchArticles()
        }

        fetchArticles()
    }

    private fun showLoading(show: Boolean) {
        binding.progressBar.visibility = if (show) View.VISIBLE else View.GONE
        binding.swipeRefreshLayout.visibility = if (show) View.GONE else View.VISIBLE
        binding.recyclerView.visibility = if (show) View.GONE else View.VISIBLE
    }

    private fun showError(message: String) {
        binding.errorLayout.root.visibility = View.VISIBLE
        binding.errorLayout.errorMessageTextView.text = message
        binding.errorLayout.retryButton.setOnClickListener {
            binding.errorLayout.root.visibility = View.GONE
            fetchArticles()
        }
        showLoading(false)
    }

    private fun fetchArticles() {
        showLoading(true)
        binding.errorLayout.root.visibility = View.GONE

        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val articles = RetrofitClient.instance.getCareGuideArticles(category)
                if (articles.isEmpty()) {
                    showError("No articles found for this category.")
                } else {
                    val adapter = CareGuideAdapter(articles) { article ->
                        val intent = Intent(requireContext(), ArticleDetailActivity::class.java)
                        intent.putExtra("article_id", article.id)
                        startActivity(intent)
                    }
                    binding.recyclerView.adapter = adapter
                    showLoading(false)
                }
            } catch (e: Exception) {
                showError("Failed to load articles. Please check your internet connection and try again.")
            } finally {
                binding.swipeRefreshLayout.isRefreshing = false
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}