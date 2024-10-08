package com.akirachix.mamamindtrial.api

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ChpDetail(
    @SerializedName("id") val chpId: Int,
    @SerializedName("first_name") val firstName: String,
    @SerializedName("last_name") val lastName: String,
    @SerializedName("village") val village: String
) : Parcelable
