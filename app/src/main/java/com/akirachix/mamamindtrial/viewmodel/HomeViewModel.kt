package com.akirachix.mamamindtrial.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akirachix.mamamindtrial.api.ApiService
import kotlinx.coroutines.launch
import retrofit2.awaitResponse

class HomeViewModel(private val apiService: ApiService) : ViewModel() {

    // LiveData for the assigned mothers count
    private val _assignedMothersCount = MutableLiveData<Int>()
    val assignedMothersCount: LiveData<Int> get() = _assignedMothersCount

    // Function to fetch CHP by email and then assigned mothers count
    fun fetchAssignedMothersCount(email: String, village: String) {
        viewModelScope.launch {
            try {
                val chpResponse = apiService.getChpByEmail(email).awaitResponse()
                if (chpResponse.isSuccessful) {
                    val chpId = chpResponse.body()?.chpId // Use chpId instead of id

                    // Log the CHP ID
                    Log.d("HomeViewModel", "Fetched CHP ID: $chpId")

                    if (chpId != null) {
                        val mothersResponse = apiService.getAssignedMothersByChpAndVillage(chpId, village).awaitResponse()
                        if (mothersResponse.isSuccessful) {
                            val mothersCount = mothersResponse.body()?.size ?: 0
                            _assignedMothersCount.value = mothersCount

                            // Log the number of assigned mothers
                            Log.d("HomeViewModel", "Number of assigned mothers: $mothersCount")
                        } else {
                            _assignedMothersCount.value = 0
                            Log.e("HomeViewModel", "Failed to fetch assigned mothers: ${mothersResponse.errorBody()}")
                        }
                    } else {
                        // Handle case where chpId is null
                        _assignedMothersCount.value = 0
                        Log.e("HomeViewModel", "CHP ID is null")
                    }
                } else {
                    _assignedMothersCount.value = 0
                    Log.e("HomeViewModel", "Failed to fetch CHP: ${chpResponse.errorBody()}")
                }
            } catch (e: Exception) {
                _assignedMothersCount.value = 0
                Log.e("HomeViewModel", "Error occurred: ${e.message}")
            }
        }
    }
}
