package com.example.sampleapp.viewmodel

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sampleapp.extensions.standardDeviation
import com.example.sampleapp.model.WeatherResponse
import com.example.sampleapp.repository.WeatherRepository
import kotlinx.coroutines.launch

class WeatherViewModel(private val weatherRepository: WeatherRepository) : ViewModel() {

    val weatherLiveData: LiveData<WeatherResponse> = MutableLiveData()

    val forecastLiveData: LiveData<Double> = MutableLiveData()

    fun getCurrentWeather() {
        if (weatherLiveData.value != null) return

        viewModelScope.launch {
            onWeatherResult(weatherRepository.getCurrentWeather())
        }
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun onWeatherResult(response: WeatherResponse?) {
        (weatherLiveData as MutableLiveData).postValue(response ?: return)
    }

    fun getForecast() {
        viewModelScope.launch {
            val forecast = weatherRepository.get5DayForecast()
            onForecastReceived(FloatArray(forecast.size) { forecast[it].tempInCelsius })
        }
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun onForecastReceived(temperatures: FloatArray) {
        (forecastLiveData as MutableLiveData).postValue(temperatures.standardDeviation)
    }

}