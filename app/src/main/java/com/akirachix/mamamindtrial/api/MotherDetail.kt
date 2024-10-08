package com.akirachix.mamamindtrial.api

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
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
    @SerializedName("village") val village: String,

    // Add fields for tracking visits
    var lastVisitDate: Date?,   // Date when the mother was last visited (null if never visited)
    var dueDate: Date,          // Date when the next visit is due
    var visitStatus: VisitStatus // Status of the visit (Due, Missed, Visited)
) : Parcelable

// Enum to track the visit status
enum class VisitStatus {
    DUE_VISIT,    // The mother is due for a visit
    MISSED_VISIT, // The mother missed a visit
    VISITED       // The mother has been visited
}
