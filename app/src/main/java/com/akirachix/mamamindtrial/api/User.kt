package com.akirachix.mamamindtrial.api

data class User(

    val id: Int? = null,
    val email: String,
    val username: String,
    val firstName: String,
    val lastName: String,
    val phoneNumber: String,
    val password: String,
    val userRole: String
)
