package com.example.sampleapp.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Wind(
    val speed: Double,
    @Json(name = "deg") val directionDegrees: Int
): Parcelable