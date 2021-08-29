package com.example.sampleapp

import com.example.sampleapp.mocks.model.weatherResponseStub
import com.example.sampleapp.model.WeatherResponse
import com.example.sampleapp.util.JsonResponseLoader
import org.junit.Test


class WeatherApiResponseTest {

    @Test
    fun testWeatherResponseBody() {
        val body = JsonResponseLoader.loadJsonToPojo<WeatherResponse>("weatherResponse.json")
        assert(body == weatherResponseStub)
    }

}