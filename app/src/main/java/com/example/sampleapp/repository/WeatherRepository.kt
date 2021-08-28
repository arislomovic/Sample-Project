package com.example.sampleapp.repository

import com.example.sampleapp.model.WeatherResponse

interface WeatherRepository {
    suspend fun getCurrentWeather(): WeatherResponse?
    suspend fun get5DayForecast(): List<WeatherResponse>
}