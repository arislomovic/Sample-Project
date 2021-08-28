package com.example.sampleapp.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Rain(@Json(name = "3h") val threeHourVolume: Int) : Parcelable