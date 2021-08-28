package com.example.sampleapp

import com.example.sampleapp.model.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface WeatherService {
    @GET("current.json")
    suspend fun getCurrentWeather(): WeatherResponse

    @GET("future_{days}.json")
    suspend fun getFutureWeather(@Path("days") days: Int): WeatherResponse
}