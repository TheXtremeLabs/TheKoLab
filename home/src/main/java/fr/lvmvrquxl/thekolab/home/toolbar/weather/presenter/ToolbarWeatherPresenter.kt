package fr.lvmvrquxl.thekolab.home.toolbar.weather.presenter

import fr.lvmvrquxl.thekolab.home.toolbar.weather.view.ToolbarWeatherView

internal interface ToolbarWeatherPresenter {
    companion object {
        const val GPS_USABLE_REQUIRED: Int = 101

        fun build(view: ToolbarWeatherView): ToolbarWeatherPresenter =
            ToolbarWeatherPresenterImpl(view)
    }

    fun launchLocationRetriever()
}