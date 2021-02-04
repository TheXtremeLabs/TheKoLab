package fr.lvmvrquxl.thekolab.home.toolbar.weather.presenter

import fr.lvmvrquxl.thekolab.home.toolbar.weather.view.HomeToolbarWeatherView

internal interface HomeToolbarWeatherPresenter {
    companion object {
        const val GPS_USABLE_REQUIRED: Int = 101

        fun build(view: HomeToolbarWeatherView): HomeToolbarWeatherPresenter =
            HomeToolbarWeatherPresenterImpl(view)
    }

    fun launchLocationRetriever()
}