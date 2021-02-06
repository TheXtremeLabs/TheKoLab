package fr.lvmvrquxl.thekolab.home.toolbar.weather.presenter

import android.app.Activity
import android.content.IntentSender
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import fr.lvmvrquxl.thekolab.core.tracker.TrackerBuilder
import fr.lvmvrquxl.thekolab.core.tracker.weather.WeatherTrackerListener
import fr.lvmvrquxl.thekolab.core.weather.dto.WeatherDTO
import fr.lvmvrquxl.thekolab.shared.presenter.Presenter
import kotlinx.coroutines.*

internal class ToolbarWeatherPresenter(
    private val activity: Activity,
    private val callback: ToolbarWeatherCallback
) : Presenter(), WeatherTrackerListener {
    companion object {
        const val GPS_USABLE_REQUIRED: Int = 101

        fun create(activity: Activity, callback: ToolbarWeatherCallback): Presenter =
            ToolbarWeatherPresenter(activity, callback)
    }

    init {
        super.trackers = TrackerBuilder().withWeather(this).build()
    }

    private var hasWeatherData: Boolean = false

    override fun getFusedLocationProviderClient(): FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(this.activity)

    override fun getSettingsClient(): SettingsClient =
        LocationServices.getSettingsClient(this.activity)

    override fun resolveLocationSettings(exception: Exception): Job =
        super.coroutineScope.launch(Dispatchers.Main) {
            if (exception is ResolvableApiException) try {
                exception.startResolutionForResult(
                    this@ToolbarWeatherPresenter.activity,
                    GPS_USABLE_REQUIRED
                )
            } catch (sendEx: IntentSender.SendIntentException) {
            }
        }

    override fun updateWeatherInfo(weather: WeatherDTO): Job =
        super.coroutineScope.launch(Dispatchers.Main) {
            this@ToolbarWeatherPresenter.callback.updateWeather(weather)
            if (!this@ToolbarWeatherPresenter.hasWeatherData) {
                this@ToolbarWeatherPresenter.callback.showWeather()
                this@ToolbarWeatherPresenter.hasWeatherData = true
            }
        }
}