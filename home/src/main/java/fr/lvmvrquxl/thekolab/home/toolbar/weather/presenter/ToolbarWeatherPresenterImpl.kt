package fr.lvmvrquxl.thekolab.home.toolbar.weather.presenter

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.IntentSender
import android.location.Location
import android.os.Looper
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.tasks.Task
import fr.lvmvrquxl.thekolab.home.core.weather.dto.WeatherDTO
import fr.lvmvrquxl.thekolab.home.core.weather.WeatherRepository
import fr.lvmvrquxl.thekolab.home.toolbar.weather.view.ToolbarWeatherView
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

internal class ToolbarWeatherPresenterImpl(private val view: ToolbarWeatherView) :
    ToolbarWeatherPresenter {
    companion object {
        private const val LOCATION_REQUEST_INTERVAL: Long = 1000
        private const val SCOPE_NAME: String = "HomeToolbarWeatherPresenterImpl"
    }

    private val coroutineScope: CoroutineScope = CoroutineScope(CoroutineName(SCOPE_NAME))
    private var locationProviderClient: FusedLocationProviderClient? = null
    private var locationRequest: LocationRequest? = null
    private var resolveApiException: Boolean = true
    private var hasWeatherData: Boolean = false

    init {
        this.locationProviderClient = this.view.activity?.let { context: Context ->
            LocationServices.getFusedLocationProviderClient(context)
        }
    }

    override fun launchLocationRetriever() {
        this.coroutineScope.launch {
            if (this.isActive) {
                val locationSettingsJob: Job =
                    this@ToolbarWeatherPresenterImpl.checkLocationSettings {
                        this@ToolbarWeatherPresenterImpl.startLocationUpdates()
                    }
                locationSettingsJob.join()
            }
        }
    }

    private fun checkLocationSettings(onSuccess: () -> Unit): Job =
        this.coroutineScope.launch(Dispatchers.Main) {
            if (this.isActive) {
                this@ToolbarWeatherPresenterImpl.locationRequest =
                    LocationRequest.create().apply {
                        this.interval = LOCATION_REQUEST_INTERVAL
                        this.priority = LocationRequest.PRIORITY_LOW_POWER
                    }
                this@ToolbarWeatherPresenterImpl.view.activity?.let { activity: Activity ->
                    val task: Task<LocationSettingsResponse>? =
                        this@ToolbarWeatherPresenterImpl.locationRequest?.let { request: LocationRequest ->
                            val settingsRequest: LocationSettingsRequest =
                                LocationSettingsRequest.Builder().addLocationRequest(request)
                                    .build()
                            val settingsClient: SettingsClient =
                                LocationServices.getSettingsClient(activity)
                            settingsClient.checkLocationSettings(settingsRequest)
                        }
                    task?.addOnSuccessListener { response: LocationSettingsResponse ->
                        if (response.locationSettingsStates.isGpsUsable) onSuccess()
                    }
                    task?.addOnFailureListener { exception: Exception ->
                        if (
                            exception is ResolvableApiException
                            && this@ToolbarWeatherPresenterImpl.resolveApiException
                            && !this@ToolbarWeatherPresenterImpl.hasWeatherData
                        )
                            try {
                                this@ToolbarWeatherPresenterImpl.resolveApiException = false
                                this@ToolbarWeatherPresenterImpl.coroutineScope.cancel()
                                exception.startResolutionForResult(
                                    activity,
                                    ToolbarWeatherPresenter.GPS_USABLE_REQUIRED
                                )
                            } catch (sendEx: IntentSender.SendIntentException) {
                            }
                    }
                }
            }
        }

    private fun retrieveWeather(location: Location): Job =
        this.coroutineScope.launch(Dispatchers.IO) {
            if (this.isActive) {
                val callback = object : Callback<WeatherDTO> {
                    override fun onFailure(call: Call<WeatherDTO>, t: Throwable) {
                        t.printStackTrace()
                    }

                    override fun onResponse(
                        call: Call<WeatherDTO>,
                        response: Response<WeatherDTO>
                    ) {
                        response.body()?.let { weather: WeatherDTO ->
                            this@ToolbarWeatherPresenterImpl.view.setLocationCity(weather.cityName)
                            this@ToolbarWeatherPresenterImpl.view.setLocationCountry(weather.system.country)
                            this@ToolbarWeatherPresenterImpl.view.setDegreeNumber(weather.mainData.temperature)
                            this@ToolbarWeatherPresenterImpl.view.setDescription(weather.weather[0].description)
                            this@ToolbarWeatherPresenterImpl.showDataOnUI()
                        }
                    }
                }
                WeatherRepository.getWeatherFromCoordinates(
                    location.latitude,
                    location.longitude,
                    callback
                )
            }
        }

    private fun showDataOnUI(): Job = this.coroutineScope.launch(Dispatchers.Main) {
        if (isActive) {
            this@ToolbarWeatherPresenterImpl.hasWeatherData = true
            delay(1000)
            this@ToolbarWeatherPresenterImpl.view.showWeatherInfo()
        }
    }

    @SuppressLint("MissingPermission")
    private fun startLocationUpdates(): Job = this.coroutineScope.launch {
        if (this.isActive) {
            this@ToolbarWeatherPresenterImpl.locationRequest?.let { request: LocationRequest ->
                val locationCallback: LocationCallback = object : LocationCallback() {
                    override fun onLocationResult(locationResult: LocationResult?) {
                        val lastLocation: Location? = locationResult?.locations?.last()
                        lastLocation?.let { location: Location ->
                            this@ToolbarWeatherPresenterImpl.retrieveWeather(location)
                        }
                    }
                }
                this@ToolbarWeatherPresenterImpl.locationProviderClient?.requestLocationUpdates(
                    request,
                    locationCallback,
                    Looper.getMainLooper()
                )
            }
        }
    }
}