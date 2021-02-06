package fr.lvmvrquxl.thekolab.home.core.tracker.weather

import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.SettingsClient
import fr.lvmvrquxl.thekolab.home.core.weather.dto.WeatherDTO
import kotlinx.coroutines.Job
import kotlin.Exception

internal interface WeatherTrackerListener {
    fun getFusedLocationProviderClient(): FusedLocationProviderClient

    fun getSettingsClient(): SettingsClient

    fun resolveLocationSettings(exception: Exception): Job

    fun updateWeatherInfo(weather: WeatherDTO): Job
}