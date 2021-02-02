package fr.lvmvrquxl.thekolab.home.presenter

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.IntentSender
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Looper
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.tasks.Task
import fr.lvmvrquxl.thekolab.home.model.WeatherDTO
import fr.lvmvrquxl.thekolab.home.model.WeatherRepository
import fr.lvmvrquxl.thekolab.home.view.HomeToolbarWeatherView
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

internal class HomeToolbarWeatherPresenterImpl(private val view: HomeToolbarWeatherView) :
    HomeToolbarWeatherPresenter {
    companion object {
        private const val LOCATION_REQUEST_INTERVAL: Long = 1000
        private const val SCOPE_NAME: String = "HomeToolbarWeatherPresenterImpl"
    }

    private val coroutineScope: CoroutineScope = CoroutineScope(CoroutineName(SCOPE_NAME))
    private var locationProviderClient: FusedLocationProviderClient? = null
    private var locationRequest: LocationRequest? = null
    private var resolveApiException: Boolean = true

    init {
        this.locationProviderClient = this.view.activity?.let { context: Context ->
            LocationServices.getFusedLocationProviderClient(context)
        }
    }

    override fun launchLocationRetriever() {
        this.coroutineScope.launch {
            if (this.isActive) {
                val locationSettingsJob: Job =
                    this@HomeToolbarWeatherPresenterImpl.checkLocationSettings {
                        this@HomeToolbarWeatherPresenterImpl.startLocationUpdates()
                    }
                locationSettingsJob.join()
            }
        }
    }

    private fun checkLocationSettings(onSuccess: () -> Unit): Job =
        this.coroutineScope.launch(Dispatchers.Main) {
            if (this.isActive) {
                this@HomeToolbarWeatherPresenterImpl.locationRequest =
                    LocationRequest.create().apply {
                        this.interval = LOCATION_REQUEST_INTERVAL
                        this.priority = LocationRequest.PRIORITY_LOW_POWER
                    }
                this@HomeToolbarWeatherPresenterImpl.view.activity?.let { activity: Activity ->
                    val task: Task<LocationSettingsResponse>? =
                        this@HomeToolbarWeatherPresenterImpl.locationRequest?.let { request: LocationRequest ->
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
                            && this@HomeToolbarWeatherPresenterImpl.resolveApiException
                        )
                            try {
                                this@HomeToolbarWeatherPresenterImpl.resolveApiException = false
                                exception.startResolutionForResult(
                                    activity,
                                    HomeToolbarWeatherPresenter.REQUEST_CHECK_SETTINGS
                                )
                            } catch (sendEx: IntentSender.SendIntentException) {
                            }
                    }
                }
            }
        }

    private fun retrieveCity(location: Location) = this.view.activity?.let { activity: Activity ->
        Geocoder(activity, Locale.getDefault())
            .getFromLocation(location.latitude, location.longitude, 1)
            .forEach { address: Address? ->
                address?.let { a: Address ->
                    this@HomeToolbarWeatherPresenterImpl.view.setLocationCity(a.locality)
                    this@HomeToolbarWeatherPresenterImpl.view.setLocationCountry(a.countryCode)

                    this@HomeToolbarWeatherPresenterImpl.retrieveWeather(location)
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
                            this@HomeToolbarWeatherPresenterImpl.view.setDegreeNumber(weather.mainData.temperature)
                            this@HomeToolbarWeatherPresenterImpl.view.setDescription(weather.weather[0].description)
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

    @SuppressLint("MissingPermission")
    private fun startLocationUpdates(): Job = this.coroutineScope.launch {
        if (this.isActive) {
            this@HomeToolbarWeatherPresenterImpl.locationRequest?.let { request: LocationRequest ->
                val locationCallback: LocationCallback = object : LocationCallback() {
                    override fun onLocationResult(locationResult: LocationResult?) {
                        val lastLocation: Location? = locationResult?.locations?.last()
                        lastLocation?.let { location: Location ->
                            this@HomeToolbarWeatherPresenterImpl.retrieveCity(location)
                        }
                    }
                }
                this@HomeToolbarWeatherPresenterImpl.locationProviderClient?.requestLocationUpdates(
                    request,
                    locationCallback,
                    Looper.getMainLooper()
                )
            }
        }
    }
}