package com.akirachix.mamamindtrial.api

data class ChpDetails(
    val id: Int? = null,
    val userId: Int,
    val registeredDate: String,
    val regNo: String,
    val phoneNumber: String,
    val location: String,
    val subLocation: String,
    val village: String,
    val numberOfMothers: Int? = null
)