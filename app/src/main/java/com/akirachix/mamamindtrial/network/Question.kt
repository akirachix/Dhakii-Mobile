package com.akirachix.mamamindtrial.network


data class Question(
    val id: Int,
    val question: String,
    val option_1: String,
    val first_score: Int,
    val option_2: String,
    val second_score: Int,
    val option_3: String,
    val third_score: Int,
    val option_4: String,
    val forth_score: Int,
    var current_score: Int? = null
)
