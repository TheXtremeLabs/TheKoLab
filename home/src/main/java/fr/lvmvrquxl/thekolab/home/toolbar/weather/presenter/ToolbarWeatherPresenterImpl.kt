package fr.lvmvrquxl.thekolab.home.toolbar.weather.presenter

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.IntentSender
import android.location.Location
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.tasks.Task
import fr.lvmvrquxl.thekolab.home.core.weather.WeatherRepository
import fr.lvmvrquxl.thekolab.home.core.weather.dto.WeatherDTO
import fr.lvmvrquxl.thekolab.home.toolbar.weather.view.ToolbarWeatherView
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

internal class ToolbarWeatherPresenterImpl(private val view: ToolbarWeatherView) :
    ToolbarWeatherPresenter {
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

    private suspend fun trackWeather(): Job = this.coroutineScope.launch {
        while (this.isActive && !this@ToolbarWeatherPresenterImpl.hasWeatherData) {
            this@ToolbarWeatherPresenterImpl.checkLocationSettings()
            this@ToolbarWeatherPresenterImpl.requestLocationUpdates()
            delay(INTERVAL)
        }
    }

    private fun checkLocationSettings() = runBlocking<Unit> {
        val settingsRequest: Deferred<LocationSettingsRequest> = this.async {
            val request: LocationRequest = LocationRequest.create().apply {
                this.interval = LOCATION_REQUEST_INTERVAL
                this.priority = LocationRequest.PRIORITY_LOW_POWER
            }
            LocationSettingsRequest.Builder()
                .addLocationRequest(request)
                .build()
        }
        val settingsClient: Deferred<SettingsClient?> = this.async {
            this@ToolbarWeatherPresenterImpl.view.activity?.let { activity: Activity ->
                LocationServices.getSettingsClient(activity)
            }
        }
        val task: Task<LocationSettingsResponse>? =
            settingsClient.await()?.checkLocationSettings(settingsRequest.await())
        task?.addOnFailureListener { exception: Exception ->
            this@ToolbarWeatherPresenterImpl.resolveLocationSettings(exception)
        }
    }

    @SuppressLint("MissingPermission")
    private fun requestLocationUpdates(): Unit = runBlocking {
        val providerClient: FusedLocationProviderClient? =
            this@ToolbarWeatherPresenterImpl.view.activity?.let { context: Context ->
                LocationServices.getFusedLocationProviderClient(context)
            }
        providerClient?.lastLocation?.addOnSuccessListener { location: Location? ->
            location?.let { l: Location -> this@ToolbarWeatherPresenterImpl.retrieveWeather(l) }
        }
    }

    private fun resolveLocationSettings(exception: Exception): Job =
        this.coroutineScope.launch(Dispatchers.Main) {
            if (exception is ResolvableApiException) try {
                this@ToolbarWeatherPresenterImpl.view.activity?.let { activity: Activity ->
                    exception.startResolutionForResult(
                        activity,
                        ToolbarWeatherPresenter.GPS_USABLE_REQUIRED
                    )
                }
            } catch (sendEx: IntentSender.SendIntentException) {
                this@ToolbarWeatherPresenterImpl.cancelCoroutines()
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

    private fun setUIData(weather: WeatherDTO): Job =
        this.coroutineScope.launch(Dispatchers.Main) {
            this@ToolbarWeatherPresenterImpl.view.setLocationCity(weather.cityName)
            this@ToolbarWeatherPresenterImpl.view.setLocationCountry(weather.system.country)
            this@ToolbarWeatherPresenterImpl.view.setDegreeNumber(weather.mainData.temperature)
            this@ToolbarWeatherPresenterImpl.view.setDescription(weather.weather[0].description)

            if (!this@ToolbarWeatherPresenterImpl.hasWeatherData) {
                this@ToolbarWeatherPresenterImpl.showWeather()
                this@ToolbarWeatherPresenterImpl.hasWeatherData = true
            }
        }

    private fun showWeather(): Job = this.coroutineScope.launch(Dispatchers.Main) {
        this@ToolbarWeatherPresenterImpl.view.showWeatherInfo()
    }

    private fun weatherCallback(): Callback<WeatherDTO> = object : Callback<WeatherDTO> {
        override fun onFailure(call: Call<WeatherDTO>, t: Throwable) {
            t.printStackTrace()
        }

        override fun onResponse(
            call: Call<WeatherDTO>,
            response: Response<WeatherDTO>
        ) {
            response.body()?.let { weather: WeatherDTO ->
                this@ToolbarWeatherPresenterImpl.setUIData(weather)
            }
        }
    }
}