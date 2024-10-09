package com.akirachix.mamamindtrial.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object SignInClient {
    private const val BASE_URL = "https://mamamind-db02af72f48f.herokuapp.com/api/users/login/"


    val retrofitInstance: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
