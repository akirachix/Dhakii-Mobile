package com.akirachix.mamamindtrial

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("https://mamamind-db02af72f48f.herokuapp.com/api/careguides/")
    suspend fun getCareGuideArticles(@Query("category") category: String): List<CareGuideArticle>

    @GET("https://mamamind-db02af72f48f.herokuapp.com/api/careguides/{id}")
    suspend fun getArticleDetails(@Path("id") id: Int): CareGuideArticle
}