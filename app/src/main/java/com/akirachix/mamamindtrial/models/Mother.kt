package com.akirachix.mamamindtrial.models

data class MotherDetail(
    val motherId: Int,
    val firstName: String,
    val lastName: String,
    val dateOfBirth: String,
    val noOfChildren: Int,
    val dateOfReg: String,
    val telNo: String,
    val maritalStatus: String,
    val nextOfKinId: Int,
    val subLocation: String,
)