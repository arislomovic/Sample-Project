package com.example.sampleapp.repository

import android.util.Log
import com.example.sampleapp.WeatherService
import com.example.sampleapp.model.WeatherResponse

private const val TAG = "WeatherRepository"

class WeatherRepositoryImpl(private val weatherService: WeatherService):WeatherRepository {

    override suspend fun getCurrentWeather(): WeatherResponse? {
        return weatherService
            .runCatching { getCurrentWeather() }
            .onFailure {
                Log.e(TAG, "getCurrentWeather Failed", it)
            }
            .onSuccess {
                Log.d(TAG, "getCurrentWeather Succeeded")
            }
            .getOrNull()
    }

    override suspend fun get5DayForecast(): List<WeatherResponse> {
        val results = ArrayList<WeatherResponse>()
        for (i in 1..5) {
            getFutureForecast(i)?.let { results.add(it) }
        }
        return results
    }

    private suspend fun getFutureForecast(days: Int): WeatherResponse? {
        return weatherService
            .runCatching { getFutureWeather(days) }
            .onFailure {
                Log.e(TAG, "getFutureForecast #$days Failed", it)
            }
            .onSuccess {
                Log.d(TAG, "getFutureForecast #$days Succeeded")
            }
            .getOrNull()
    }
}