package com.akirachix.mamamindtrial.models

data class LoginResponse(
    var id : Int,
    var username : String,
    var email : String,
    var firstname : String,
    var lastname : String,
    var accessToken : String,
    var refreshToken : String
)
