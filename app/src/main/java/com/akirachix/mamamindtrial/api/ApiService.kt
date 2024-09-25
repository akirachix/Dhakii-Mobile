
package com.akirachix.mamamindtrial.api
import com.akirachix.mamamindtrial.CareGuideArticle

<<<<<<< HEAD
import com.akirachix.mamamindtrial.api.MotherDetail
import com.akirachix.mamamindtrial.models.CHP

import retrofit2.Call
=======
>>>>>>> b49109630318d559956f844629120a8a1d7f3941
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
    @GET("api/chps/")
    fun getChps(): Call<List<CHP>>
    }

<<<<<<< HEAD
=======
}
>>>>>>> b49109630318d559956f844629120a8a1d7f3941
