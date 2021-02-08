package fr.lvmvrquxl.thekolab.core.tracker.weather

import android.annotation.SuppressLint
import android.location.Location
import com.google.android.gms.location.*
import com.google.android.gms.tasks.Task
import fr.lvmvrquxl.thekolab.core.tracker.Tracker
import fr.lvmvrquxl.thekolab.core.weather.WeatherRepository
import fr.lvmvrquxl.thekolab.core.weather.dto.WeatherDTO
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Weather tracker.
 *
 * @param listener Listener of weather tracker
 *
 * @since 0.1.3
 *
 * @see [Tracker]
 * @see [WeatherTrackerListener]
 */
internal class WeatherTracker(private val listener: WeatherTrackerListener) : Tracker() {
    companion object {
        private const val INTERVAL: Long = 5000
    }

    private var hasWeatherData: Boolean = false

    override suspend fun start(): Job = super.coroutineScope.launch(Dispatchers.Default) {
        while (this.isActive && !this@WeatherTracker.hasWeatherData) {
            this@WeatherTracker.checkLocationSettings()
            this@WeatherTracker.requestLocationUpdates()
            delay(INTERVAL)
        }
    }

    private fun checkLocationSettings() = runBlocking<Unit>(Dispatchers.Default) {
        val settingsRequest: Deferred<LocationSettingsRequest> = this.async {
            val request: LocationRequest = LocationRequest.create().apply {
                this.interval = INTERVAL
                this.priority = LocationRequest.PRIORITY_LOW_POWER
            }
            LocationSettingsRequest.Builder()
                .addLocationRequest(request)
                .build()
        }
        val settingsClient: Deferred<SettingsClient> = this.async {
            this@WeatherTracker.listener.getSettingsClient()
        }
        val task: Task<LocationSettingsResponse>? = settingsClient.await()
            .checkLocationSettings(settingsRequest.await())
        task?.addOnFailureListener { exception: Exception ->
            this@WeatherTracker.listener.resolveLocationSettings(exception)
        }
    }

    @SuppressLint("MissingPermission")
    private fun requestLocationUpdates(): Unit = runBlocking(Dispatchers.Default) {
        val providerClient: FusedLocationProviderClient =
            this@WeatherTracker.listener.getFusedLocationProviderClient()
        providerClient.lastLocation?.addOnSuccessListener { location: Location? ->
            location?.let { l: Location -> this@WeatherTracker.retrieveWeather(l) }
        }
    }

    private fun retrieveWeather(location: Location): Job =
        super.coroutineScope.launch(Dispatchers.IO) {
            val callback: Callback<WeatherDTO> = this@WeatherTracker.weatherCallback()
            WeatherRepository.getWeatherFromCoordinates(
                location.latitude,
                location.longitude,
                callback
            )
        }

    private fun weatherCallback(): Callback<WeatherDTO> = object : Callback<WeatherDTO> {
        override fun onFailure(call: Call<WeatherDTO>, t: Throwable) {}

        override fun onResponse(call: Call<WeatherDTO>, response: Response<WeatherDTO>) {
            response.body()?.let { weather: WeatherDTO ->
                this@WeatherTracker.listener.updateWeatherInfo(weather)
                this@WeatherTracker.hasWeatherData = true
            }
        }
    }
}