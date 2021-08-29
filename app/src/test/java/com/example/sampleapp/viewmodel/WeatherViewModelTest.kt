package com.example.sampleapp.viewmodel

import com.example.sampleapp.WeatherService
import com.example.sampleapp.mocks.model.weatherResponseStub
import com.example.sampleapp.repository.WeatherRepository
import com.example.sampleapp.repository.WeatherRepositoryImpl
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.spyk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.lang.Exception

@ExperimentalCoroutinesApi
class WeatherViewModelTest {

    lateinit var weatherService: WeatherService
    lateinit var repository: WeatherRepository

    lateinit var viewModel: WeatherViewModel

    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {

        weatherService = mockk(relaxed = true)
        repository = spyk(WeatherRepositoryImpl(weatherService))
        viewModel = spyk(WeatherViewModel(repository))

        Dispatchers.setMain(testDispatcher)
    }


    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun testGetCurrentWeatherSuccess() {
        coEvery { weatherService.getCurrentWeather() }.returns(weatherResponseStub)
        runBlockingTest {
            viewModel.getCurrentWeather()
        }
        coVerify {
            viewModel.onWeatherResult(weatherResponseStub)
        }
    }

    @Test
    fun testGetCurrentWeatherFail() {
        coEvery { weatherService.getCurrentWeather() }.throws(Exception())
        runBlockingTest {
            viewModel.getCurrentWeather()
        }
        coVerify {
            viewModel.onWeatherResult(null)
        }
    }

    @Test
    fun testGetForecastSuccess() {
        for (i in 1..5) {
            val stub = weatherResponseStub.run {
                copy(weather = weather.copy(temperature = i * 10.toFloat()))
            }
            coEvery { weatherService.getFutureWeather(i) }.returns(stub)
        }

        coEvery { weatherService.getFutureWeather(3) }.throws(Exception())

        runBlockingTest {
            viewModel.getForecast()
        }
        coVerify {
            viewModel.onForecastReceived(floatArrayOf(10f, 20f, 40f, 50f))
        }
    }
}