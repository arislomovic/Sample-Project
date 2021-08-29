package com.example.sampleapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.sampleapp.databinding.SampleFragmentBinding
import com.example.sampleapp.viewmodel.WeatherViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.core.view.isVisible
import com.example.sampleapp.model.WeatherResponse


class SampleFragment : Fragment() {

    private val weatherViewModel: WeatherViewModel by viewModel()
    lateinit var binding: SampleFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, s: Bundle?): View {
        binding = SampleFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindObservers()
        weatherViewModel.getCurrentWeather()
        binding.forecastButton.setOnClickListener { weatherViewModel.getForecast() }
    }

    private fun bindObservers() {
        weatherViewModel.weatherLiveData.observe(viewLifecycleOwner, this::onWeatherResult)
        weatherViewModel.forecastLiveData.observe(viewLifecycleOwner, this::onForecastResult)
    }

    private fun onWeatherResult(response: WeatherResponse) {
        binding.temperature.text = getTemperatureString(response)
        binding.windSpeed.text = response.wind.speed.toString()
        binding.cloudImage.isVisible = response.isCloudy
    }

    private fun onForecastResult(standardDeviation: Double) {
        binding.forecastStandardDeviation.text = standardDeviation.toString()
    }

    private fun getTemperatureString(response: WeatherResponse) =
        getString(R.string.temperature, response.tempInCelsius, response.tempInFahrenheit)

}