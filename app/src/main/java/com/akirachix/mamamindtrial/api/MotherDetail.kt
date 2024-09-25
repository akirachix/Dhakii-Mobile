package com.akirachix.mamamindtrial.api
import com.google.gson.annotations.SerializedName

import android.os.Parcel
import android.os.Parcelable

data class MotherDetail(
    @SerializedName("id") val motherId: Int,
    @SerializedName("first_name") val firstName: String,
    @SerializedName("last_name") val lastName: String,
    @SerializedName("date_of_birth") val dateOfBirth: String,
    @SerializedName("no_of_children") val noOfChildren: Int,
    @SerializedName("date_of_reg") val dateOfReg: String,
    @SerializedName("tel_no") val telNo: String,
    @SerializedName("marital_status") val maritalStatus: String,
    @SerializedName("sub_location") val subLocation: String,
    @SerializedName("village") val village: String
)

