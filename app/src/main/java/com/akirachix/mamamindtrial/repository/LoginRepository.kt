package com.akirachix.mamamindtrial.repository

import com.akirachix.mamamindtrial.api.ApiClient
import com.akirachix.mamamindtrial.api.ApiInterface
import com.akirachix.mamamindtrial.models.LoginRequest
import com.akirachix.mamamindtrial.models.LoginResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class LoginRepository {
    private val apiClient = ApiClient.createService(ApiInterface::class.java)

    suspend fun login(loginRequest: LoginRequest): Response<LoginResponse> {
        return withContext(Dispatchers.IO) {
            apiClient.login(loginRequest)
        }
    }
}