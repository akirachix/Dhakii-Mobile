package com.akirachix.mamamindtrial.api

import com.akirachix.mamamindtrial.api.MotherDetail

import retrofit2.Call
import retrofit2.http.GET


interface ApiService {
    @GET("api/mothers/")
    fun getMothersList(): Call<List<MotherDetail>>


}