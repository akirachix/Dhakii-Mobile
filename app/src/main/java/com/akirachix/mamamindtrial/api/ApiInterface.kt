package com.akirachix.mamamindtrial.api

import com.akirachix.mamamindtrial.models.LoginRequest
import com.akirachix.mamamindtrial.models.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {

    @POST("api/users/login/")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>
}