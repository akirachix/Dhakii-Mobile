package com.akirachix.mamamindtrial.models

class LogInRequest {
    data class LoginRequest(
        val username: String,
        val password: String
    )


    data class LoginResponse(
        val status: String,
        val message: String
    )

}