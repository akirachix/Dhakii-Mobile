//package com.akirachix.mamamindtrial.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData

class CareGuideViewModel(private val repository: CareGuideRepository) : ViewModel() {
    fun getArticlesByCategory(category: String) = repository.getArticlesByCategory(category).asLiveData()
}

