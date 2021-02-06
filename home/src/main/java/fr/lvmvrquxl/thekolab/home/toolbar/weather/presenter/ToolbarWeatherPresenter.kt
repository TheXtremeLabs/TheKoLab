package fr.lvmvrquxl.thekolab.home.toolbar.weather.presenter

import android.app.Activity

internal interface ToolbarWeatherPresenter {
    companion object {
        const val GPS_USABLE_REQUIRED: Int = 101

        fun create(
            activity: Activity,
            callback: ToolbarWeatherCallback
        ): ToolbarWeatherPresenter = ToolbarWeatherPresenterImpl(activity, callback)
    }

    fun cancelCoroutines()

    fun startBackgroundCoroutines()
}