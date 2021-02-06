package fr.lvmvrquxl.thekolab.home.toolbar.weather.presenter

import fr.lvmvrquxl.thekolab.core.weather.dto.WeatherDTO

internal interface ToolbarWeatherCallback {
    fun showWeather()

    fun updateWeather(weather: WeatherDTO)
}