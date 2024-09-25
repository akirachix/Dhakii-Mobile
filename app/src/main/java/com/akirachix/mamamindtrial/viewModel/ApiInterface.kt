package com.akirachix.mamamindtrial.api
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.GET
import retrofit2.http.PUT


import com.akirachix.mamamindtrial.models.LogInRequest


interface ApiInterface {
    @POST("login_endpoint") // Replace with your actual endpoint
    fun login(@Body request: LogInRequest.LoginRequest): Call<LogInRequest.LoginResponse>
<<<<<<< HEAD:app/src/main/java/com/akirachix/mamamindtrial/api/ApiInterface.kt


}

=======
}
>>>>>>> b49109630318d559956f844629120a8a1d7f3941:app/src/main/java/com/akirachix/mamamindtrial/viewModel/ApiInterface.kt
