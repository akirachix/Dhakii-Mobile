
package com.akirachix.mamamindtrial.api
import com.akirachix.mamamindtrial.CareGuideArticle

import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("api/careguides/")
    suspend fun getCareGuideArticles(@Query("category") category: String): List<CareGuideArticle>

    @GET("api/careguides/{id}")
    suspend fun getArticleDetails(@Path("id") id: Int): CareGuideArticle

    @GET("api/mothers/")
    fun getMothersList(): Call<List<MotherDetail>>

}
