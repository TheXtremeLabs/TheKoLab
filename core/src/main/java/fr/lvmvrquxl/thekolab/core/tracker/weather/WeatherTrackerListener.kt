package fr.lvmvrquxl.thekolab.core.tracker.weather

import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.SettingsClient
import fr.lvmvrquxl.thekolab.core.weather.dto.WeatherDTO
import kotlinx.coroutines.Job
import kotlin.Exception

interface WeatherTrackerListener {
    fun getFusedLocationProviderClient(): FusedLocationProviderClient

    fun getSettingsClient(): SettingsClient

    fun resolveLocationSettings(exception: Exception): Job

    fun updateWeatherInfo(weather: WeatherDTO): Job
}