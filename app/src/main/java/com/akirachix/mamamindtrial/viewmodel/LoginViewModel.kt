package com.akirachix.mamamindtrial.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akirachix.mamamindtrial.models.LoginRequest
import com.akirachix.mamamindtrial.models.LoginResponse
import com.akirachix.mamamindtrial.repository.LoginRepository
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val loginRepository = LoginRepository()

    private val _loginLiveData = MutableLiveData<LoginResponse>()
    val loginLiveData: LiveData<LoginResponse> = _loginLiveData

    private val _errorLiveData = MutableLiveData<String>()
    val errorLiveData: LiveData<String> = _errorLiveData

    fun loginUser(email: String, password: String) {
        val loginRequest = LoginRequest(email, password)

        viewModelScope.launch {
            try {
                val response = loginRepository.login(loginRequest)
                if (response.isSuccessful) {
                    _loginLiveData.postValue(response.body())
                } else {
                    _errorLiveData.postValue("Login Failed: ${response.message()}")
                }
            } catch (e: Exception) {
                _errorLiveData.postValue("Error: ${e.localizedMessage}")
            }
        }
    }
}