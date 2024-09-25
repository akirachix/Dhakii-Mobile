package com.akirachix.mamamindtrial.network

import retrofit2.Call
import retrofit2.http.GET


interface ApiService {

    @GET("api/questions/")
    fun getQuestions(): Call<List<Question>>
}

