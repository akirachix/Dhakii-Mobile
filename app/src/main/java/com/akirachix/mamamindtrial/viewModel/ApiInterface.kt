package com.akirachix.mamamindtrial.api
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import com.akirachix.mamamindtrial.models.LogInRequest


interface ApiInterface {
    @POST("login_endpoint") // Replace with your actual endpoint
    fun login(@Body request: LogInRequest.LoginRequest): Call<LogInRequest.LoginResponse>
}