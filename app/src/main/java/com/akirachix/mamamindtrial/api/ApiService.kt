
package com.akirachix.mamamindtrial.api
import com.akirachix.mamamindtrial.CareGuideArticle

import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
<<<<<<< HEAD
    @GET("api/careguides/")
    suspend fun getCareGuideArticles(@Query("category") category: String): List<CareGuideArticle>

    @GET("api/careguides/{id}")
    suspend fun getArticleDetails(@Path("id") id: Int): CareGuideArticle

=======
>>>>>>> bef1f98eb6944153bd050f474211d112eab46e64
    @GET("api/mothers/")
    fun getMothersList(): Call<List<MotherDetail>>

}
