package com.akirachix.mamamindtrial.api

import com.akirachix.mamamindtrial.network.Question
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    // Fetch the list of all mothers
    @GET("api/mothers/")
    fun getMothersList(): Call<List<MotherDetail>>

    // Fetch the list of all CHPs
    @GET("api/chps/")
    fun getChps(): Call<List<ChpDetail>>

    // Fetch the list of assigned mothers by CHP and village
    @GET("api/mothers/assigned")
    fun getAssignedMothersByChpAndVillage(
        @Query("chpId") chpId: Int,
        @Query("village") village: String
    ): Call<List<MotherDetail>>

    // Fetch the list of questions
    @GET("api/questions/")
    fun getQuestions(): Call<List<Question>>

    // Fetch CHP details by email
    @GET("api/chps/byEmail")
    fun getChpByEmail(
        @Query("email") email: String
    ): Call<ChpDetail>

    companion object {
        private const val BASE_URL = "https://mamamind-db02af72f48f.herokuapp.com/"

        fun create(): ApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(ApiService::class.java)
        }
    }
}
