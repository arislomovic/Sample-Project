package com.example.sampleapp.di

import com.squareup.moshi.Moshi
import com.example.sampleapp.WeatherService
import com.example.sampleapp.repository.WeatherRepository
import com.example.sampleapp.repository.WeatherRepositoryImpl
import com.example.sampleapp.viewmodel.WeatherViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val weatherModule = module {

    factory {
        Moshi.Builder().build()
    }

    factory<WeatherService> {
        Retrofit
            .Builder()
            .baseUrl("SOME WEATHER URL")
            .addConverterFactory(MoshiConverterFactory.create(get()))
            .build()
            .create(WeatherService::class.java)
    }

    factory<WeatherRepository> {
        WeatherRepositoryImpl(get())
    }

    viewModel {
        WeatherViewModel(get())
    }
}