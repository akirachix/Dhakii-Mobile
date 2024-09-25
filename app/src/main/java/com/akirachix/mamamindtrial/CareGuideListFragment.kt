package com.akirachix.mamamindtrial

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.akirachix.mamamindtrial.databinding.FragmentCareGuideListBinding
import com.akirachix.mamamindtrial.api.RetrofitClient
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
        inflater: LayoutInflater, container: ViewGroup?,
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

    private fun fetchArticles() {
        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val articles = RetrofitClient.apiService.getCareGuideArticles(category)
                if (articles.isNotEmpty()) {
                    val adapter = CareGuideAdapter(articles, category) { article ->
                        val intent = Intent(requireContext(), ArticleDetailActivity::class.java)
                        intent.putExtra("article_id", article.id)
                        intent.putExtra("article_category", category) // Pass the category
                        startActivity(intent)
                    }
                    binding.recyclerView.adapter = adapter
                }
            } catch (e: Exception) {
                // Handle error
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}






//package com.akirachix.mamamindtrial
//
//import android.content.Intent
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.fragment.app.Fragment
//import androidx.lifecycle.lifecycleScope
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.akirachix.mamamindtrial.databinding.FragmentCareGuideListBinding
//import com.akirachix.mamamindtrial.api.RetrofitClient
//import com.akirachix.mamamindtrial.ui.ArticleDetailActivity
//import kotlinx.coroutines.launch
//
//
//class CareGuideListFragment : Fragment() {
//
//
//    private var _binding: FragmentCareGuideListBinding? = null
//    private val binding get() = _binding!!
//
//
//    private lateinit var category: String
//
//
//    companion object {
//        private const val ARG_CATEGORY = "category"
//
//
//        fun newInstance(category: String): CareGuideListFragment {
//            return CareGuideListFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_CATEGORY, category)
//                }
//            }
//        }
//    }
//
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        arguments?.let {
//            category = it.getString(ARG_CATEGORY) ?: ""
//        }
//    }
//
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        _binding = FragmentCareGuideListBinding.inflate(inflater, container, false)
//        return binding.root
//    }
//
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//
//        binding.recyclerView.layoutManager = LinearLayoutManager(context)
//
//
//        binding.swipeRefreshLayout.setOnRefreshListener {
//            fetchArticles()
//        }
//
//
//        fetchArticles()
//    }
//
//
//    private fun showLoading(show: Boolean) {
//        binding.progressBar.visibility = if (show) View.VISIBLE else View.GONE
//        binding.swipeRefreshLayout.visibility = if (show) View.GONE else View.VISIBLE
//        binding.recyclerView.visibility = if (show) View.GONE else View.VISIBLE
//    }
//
//
//    private fun fetchArticles() {
//        showLoading(true)
//        viewLifecycleOwner.lifecycleScope.launch {
//            try {
//                val articles = RetrofitClient.apiService.getCareGuideArticles(category)
//                if (articles.isEmpty()) {
//                    // Show error
//                } else {
//                    val adapter = CareGuideAdapter(articles, category) { article ->
//                        val intent = Intent(requireContext(), ArticleDetailActivity::class.java)
//                        intent.putExtra("article_id", article.id)
//                        startActivity(intent)
//                    }
//                    binding.recyclerView.adapter = adapter
//                    showLoading(false)
//                }
//            } catch (e: Exception) {
//                // Handle error
//            } finally {
//                binding.swipeRefreshLayout.isRefreshing = false
//            }
//        }
//    }
//
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
//}
//
//
//
//
//
//
//
