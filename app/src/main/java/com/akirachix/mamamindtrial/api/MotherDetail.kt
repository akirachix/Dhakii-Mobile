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
    @SerializedName("registered_date") val registeredDate: String,
    @SerializedName("updated_at") val updatedAt: String,

    var lastVisitDate: Date?,   // Date when the mother was last visited (null if never visited)
    var dueDate: Date?,         // Date when the next visit is due (can be null)

    @SerializedName("status") private var statusInt: Int
) : Parcelable {

    // Map the statusInt to VisitStatus enum correctly
    var visitStatus: VisitStatus
        get() = when (statusInt) {
            1 -> VisitStatus.DUE_VISIT
            0 -> VisitStatus.VISITED
            -1 -> VisitStatus.MISSED_VISIT
            else -> VisitStatus.DUE_VISIT // Default to DUE_VISIT if unknown value
        }
        set(value) {
            statusInt = when (value) {
                VisitStatus.DUE_VISIT -> 1
                VisitStatus.VISITED -> 0
                VisitStatus.MISSED_VISIT -> -1
            }
        }

    // A derived boolean to check if mother is "visited"
    val isVisited: Boolean
        get() = statusInt == 0
}

// Enum class to represent visit statuses
enum class VisitStatus {
    DUE_VISIT,    // The mother is due for a visit
    MISSED_VISIT, // The mother missed a visit
    VISITED       // The mother has been visited
}