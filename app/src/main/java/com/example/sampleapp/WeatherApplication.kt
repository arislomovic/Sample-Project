package com.example.sampleapp

import android.app.Application
import com.example.sampleapp.di.weatherModule
import com.example.sampleapp.repository.WeatherRepository
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import retrofit2.Retrofit

class WeatherApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@WeatherApplication)
            modules(
                weatherModule
            )
        }
    }

}