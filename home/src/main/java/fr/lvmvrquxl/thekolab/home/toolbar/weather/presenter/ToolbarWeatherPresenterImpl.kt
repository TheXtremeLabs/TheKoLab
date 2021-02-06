package fr.lvmvrquxl.thekolab.home.toolbar.weather.presenter

import android.annotation.SuppressLint
import android.app.Activity
import android.content.IntentSender
import android.location.Location
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.tasks.Task
import fr.lvmvrquxl.thekolab.home.core.weather.WeatherRepository
import fr.lvmvrquxl.thekolab.home.core.weather.dto.WeatherDTO
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

// TODO: Split in a WeatherTracker class

internal class ToolbarWeatherPresenterImpl(
    private val activity: Activity,
    private val callback: ToolbarWeatherCallback
) : ToolbarWeatherPresenter {
    companion object {
        private const val INTERVAL: Long = 5000
        private const val LOCATION_REQUEST_INTERVAL: Long = 1000
        private const val SCOPE_NAME: String = "ToolbarWeatherPresenter"
    }

    private val coroutineScope: CoroutineScope = CoroutineScope(CoroutineName(SCOPE_NAME))
    private var hasWeatherData: Boolean = false

    override fun cancelCoroutines() = runBlocking(Dispatchers.Default) {
        this@ToolbarWeatherPresenterImpl.coroutineScope.cancel()
    }

    override fun startBackgroundCoroutines() = runBlocking<Unit>(Dispatchers.Default) {
        this@ToolbarWeatherPresenterImpl.trackWeather()
    }

    private suspend fun trackWeather(): Job = this.coroutineScope.launch(Dispatchers.Default) {
        while (this.isActive && !this@ToolbarWeatherPresenterImpl.hasWeatherData) {
            this@ToolbarWeatherPresenterImpl.checkLocationSettings()
            this@ToolbarWeatherPresenterImpl.requestLocationUpdates()
            delay(INTERVAL)
        }
    }

    private fun checkLocationSettings() = runBlocking<Unit>(Dispatchers.Default) {
        val settingsRequest: Deferred<LocationSettingsRequest> = this.async {
            val request: LocationRequest = LocationRequest.create().apply {
                this.interval = LOCATION_REQUEST_INTERVAL
                this.priority = LocationRequest.PRIORITY_LOW_POWER
            }
            LocationSettingsRequest.Builder()
                .addLocationRequest(request)
                .build()
        }
        val settingsClient: Deferred<SettingsClient> = this.async {
            LocationServices.getSettingsClient(this@ToolbarWeatherPresenterImpl.activity)
        }
        val task: Task<LocationSettingsResponse>? = settingsClient.await()
            .checkLocationSettings(settingsRequest.await())
        task?.addOnFailureListener { exception: Exception ->
            this@ToolbarWeatherPresenterImpl.resolveLocationSettings(exception)
        }
    }

    @SuppressLint("MissingPermission")
    private fun requestLocationUpdates(): Unit = runBlocking(Dispatchers.Default) {
        val providerClient: FusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(
                this@ToolbarWeatherPresenterImpl.activity
            )
        providerClient.lastLocation?.addOnSuccessListener { location: Location? ->
            location?.let { l: Location -> this@ToolbarWeatherPresenterImpl.retrieveWeather(l) }
        }
    }

    private fun resolveLocationSettings(exception: Exception): Job =
        this.coroutineScope.launch(Dispatchers.Main) {
            if (exception is ResolvableApiException) try {
                exception.startResolutionForResult(
                    this@ToolbarWeatherPresenterImpl.activity,
                    ToolbarWeatherPresenter.GPS_USABLE_REQUIRED
                )
            } catch (sendEx: IntentSender.SendIntentException) {
            }
        }

    private fun retrieveWeather(location: Location): Job =
        this.coroutineScope.launch(Dispatchers.IO) {
            val callback: Callback<WeatherDTO> = this@ToolbarWeatherPresenterImpl.weatherCallback()
            WeatherRepository.getWeatherFromCoordinates(
                location.latitude,
                location.longitude,
                callback
            )
        }

    private fun setUIData(weather: WeatherDTO): Job = this.coroutineScope.launch(Dispatchers.Main) {
        this@ToolbarWeatherPresenterImpl.callback.updateWeather(weather)
        if (!this@ToolbarWeatherPresenterImpl.hasWeatherData) {
            this@ToolbarWeatherPresenterImpl.callback.showWeather()
            this@ToolbarWeatherPresenterImpl.hasWeatherData = true
        }
    }

    private fun weatherCallback(): Callback<WeatherDTO> = object : Callback<WeatherDTO> {
        override fun onFailure(call: Call<WeatherDTO>, t: Throwable) {}

        override fun onResponse(call: Call<WeatherDTO>, response: Response<WeatherDTO>) {
            response.body()?.let { weather: WeatherDTO ->
                this@ToolbarWeatherPresenterImpl.setUIData(weather)
            }
        }
    }
}