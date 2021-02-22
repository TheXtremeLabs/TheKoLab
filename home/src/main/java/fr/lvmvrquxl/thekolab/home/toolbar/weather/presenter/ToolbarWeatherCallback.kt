package fr.lvmvrquxl.thekolab.home.toolbar.weather.presenter

import fr.lvmvrquxl.thekolab.core.weather.dto.WeatherDTO

/**
 * Callback of the toolbar's weather.
 *
 * @since 1.0.0
 */
internal interface ToolbarWeatherCallback {
    /**
     * Show weather info.
     *
     * @since 1.0.0
     */
    fun showWeather()

    /**
     * Update weather info.
     *
     * @param weather Next weather info
     *
     * @since 1.0.0
     *
     * @see [WeatherDTO]
     */
    fun updateWeather(weather: WeatherDTO)
}