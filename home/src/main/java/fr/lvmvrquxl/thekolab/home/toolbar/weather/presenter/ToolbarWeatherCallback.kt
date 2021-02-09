package fr.lvmvrquxl.thekolab.home.toolbar.weather.presenter

import fr.lvmvrquxl.thekolab.core.weather.dto.WeatherDTO

/**
 * Callback of the toolbar's weather.
 *
 * @since 0.1.3
 */
internal interface ToolbarWeatherCallback {
    /**
     * Show weather info.
     *
     * @since 0.1.3
     */
    fun showWeather()

    /**
     * Update weather info.
     *
     * @param weather Next weather info
     *
     * @since 0.1.3
     *
     * @see [WeatherDTO]
     */
    fun updateWeather(weather: WeatherDTO)
}