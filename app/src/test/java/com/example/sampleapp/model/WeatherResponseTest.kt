package com.example.sampleapp.model

import com.example.sampleapp.extensions.celsiusToFahrenheit
import com.example.sampleapp.mocks.model.weatherResponseStub
import org.junit.Test

class WeatherResponseTest {
    @Test
    fun testIsCloudy() {
        assert(weatherResponseStub.copy(clouds = Cloud(cloudiness = 51)).isCloudy)
        assert(!weatherResponseStub.copy(clouds = Cloud(cloudiness = 50)).isCloudy)
    }

    @Test
    fun testTemperatureConversion() = with(weatherResponseStub) {
        assert(tempInCelsius == weather.temperature)
        assert(tempInFahrenheit == tempInCelsius.celsiusToFahrenheit())
    }
}