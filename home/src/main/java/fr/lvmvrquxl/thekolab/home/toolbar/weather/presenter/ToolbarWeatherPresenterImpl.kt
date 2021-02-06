package fr.lvmvrquxl.thekolab.home.toolbar.weather.presenter

import android.app.Activity
import android.content.IntentSender
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import fr.lvmvrquxl.thekolab.home.core.tracker.Tracker
import fr.lvmvrquxl.thekolab.home.core.tracker.TrackerBuilder
import fr.lvmvrquxl.thekolab.home.core.tracker.weather.WeatherTrackerListener
import fr.lvmvrquxl.thekolab.home.core.weather.dto.WeatherDTO
import kotlinx.coroutines.*

internal class ToolbarWeatherPresenterImpl(
    private val activity: Activity,
    private val callback: ToolbarWeatherCallback
) : ToolbarWeatherPresenter, WeatherTrackerListener {
    companion object {
        private const val SCOPE_NAME: String = "ToolbarWeatherPresenter"
    }

    private val coroutineScope: CoroutineScope = CoroutineScope(CoroutineName(SCOPE_NAME))
    private val trackers: List<Tracker> = TrackerBuilder().withWeather(this).build()
    private var hasWeatherData: Boolean = false

    override fun cancelCoroutines() = runBlocking(Dispatchers.Default) {
        this@ToolbarWeatherPresenterImpl.trackers.forEach { t: Tracker -> t.cancelCoroutines() }
        this@ToolbarWeatherPresenterImpl.coroutineScope.cancel()
    }

    override fun getFusedLocationProviderClient(): FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(this.activity)

    override fun getSettingsClient(): SettingsClient =
        LocationServices.getSettingsClient(this.activity)

    override fun resolveLocationSettings(exception: Exception): Job =
        this.coroutineScope.launch(Dispatchers.Main) {
            if (exception is ResolvableApiException) try {
                exception.startResolutionForResult(
                    this@ToolbarWeatherPresenterImpl.activity,
                    ToolbarWeatherPresenter.GPS_USABLE_REQUIRED
                )
            } catch (sendEx: IntentSender.SendIntentException) {
            }
        }

    override fun startBackgroundCoroutines() = runBlocking(Dispatchers.Default) {
        this@ToolbarWeatherPresenterImpl.trackers.forEach { t: Tracker -> t.start() }
    }

    override fun updateWeatherInfo(weather: WeatherDTO): Job =
        this.coroutineScope.launch(Dispatchers.Main) {
            this@ToolbarWeatherPresenterImpl.callback.updateWeather(weather)
            if (!this@ToolbarWeatherPresenterImpl.hasWeatherData) {
                this@ToolbarWeatherPresenterImpl.callback.showWeather()
                this@ToolbarWeatherPresenterImpl.hasWeatherData = true
            }
        }
}