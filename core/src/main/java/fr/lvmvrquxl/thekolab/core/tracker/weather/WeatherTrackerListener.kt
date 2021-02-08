package fr.lvmvrquxl.thekolab.core.tracker.weather

import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.SettingsClient
import fr.lvmvrquxl.thekolab.core.weather.dto.WeatherDTO
import kotlinx.coroutines.Job
import kotlin.Exception

/**
 * Listener of the weather tracker.
 *
 * @since 0.1.3
 *
 * @see [WeatherTracker]
 */
interface WeatherTrackerListener {
    /**
     * Get location's provider.
     *
     * @return The location's provider
     *
     * @since 0.1.3
     *
     * @see [FusedLocationProviderClient]
     */
    fun getFusedLocationProviderClient(): FusedLocationProviderClient

    /**
     * Get location's settings.
     *
     * @return Location's settings of the running device
     *
     * @since 0.1.3
     *
     * @see [SettingsClient]
     */
    fun getSettingsClient(): SettingsClient

    /**
     * Resolve issues about location's settings.
     *
     * @param exception Exception that was thrown
     *
     * @return Job of the coroutine that resolves the location settings
     *
     * @since 0.1.3
     *
     * @see [Exception]
     * @see [Job]
     */
    fun resolveLocationSettings(exception: Exception): Job

    /**
     * Update weather info.
     *
     * @param weather New weather info
     *
     * @return Job of the coroutine that updates the weather info
     *
     * @since 0.1.3
     *
     * @see [Job]
     * @see [WeatherDTO]
     */
    fun updateWeatherInfo(weather: WeatherDTO): Job
}