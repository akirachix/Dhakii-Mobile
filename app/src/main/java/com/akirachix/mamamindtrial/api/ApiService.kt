
package com.akirachix.mamamindtrial.api

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

//    @GET("api/careguides/")
//    suspend fun getCareGuideArticles(@Query("category") category: String): List<CareGuideArticle>
//
//    @GET("api/careguides/{id}")
//    suspend fun getArticleDetails(@Path("id") id: Int): CareGuideArticle


    @GET("api/mothers/")
    fun getMothersList(): Call<List<MotherDetail>>

    @GET("api/chps/") // No leading slash to match the mothers API endpoint format
    fun getChps(): Call<List<ChpDetail>>

}
