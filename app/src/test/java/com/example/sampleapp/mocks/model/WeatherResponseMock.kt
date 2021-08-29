package com.example.sampleapp.mocks.model

import com.example.sampleapp.model.Cloud
import com.example.sampleapp.model.Coordinate
import com.example.sampleapp.model.Rain
import com.example.sampleapp.model.Weather
import com.example.sampleapp.model.WeatherResponse
import com.example.sampleapp.model.Wind

val weatherResponseStub = WeatherResponse(
    city = "San Francisco",
    coordinate = Coordinate(
        lon = -122.42,
        lat = 37.77
    ),
    weather = Weather(
        temperature = 14.77f,
        pressure = 1007,
        humidity = 85
    ),
    wind = Wind(
        speed = .51,
        directionDegrees = 284
    ),
    rain = Rain(threeHourVolume = 1),
    clouds = Cloud(
        cloudiness = 65
    )
)