package com.example.sampleapp.model

import android.os.Parcelable
import com.example.sampleapp.extensions.celsiusToFahrenheit
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class WeatherResponse(
    val weather: Weather,
    val rain: Rain,
    val wind: Wind,
    @Json(name = "name") val city: String,
    @Json(name = "coord") val coordinate: Coordinate,
    @Json(name = "clouds") val clouds: Cloud
):Parcelable {
    val isCloudy get() = clouds.cloudiness > 50

    val tempInCelsius get() = weather.temperature

    val tempInFahrenheit get() = tempInCelsius.celsiusToFahrenheit()

}