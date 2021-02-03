package fr.lvmvrquxl.thekolab.home.presenter

import fr.lvmvrquxl.thekolab.home.view.HomeToolbarWeatherView

internal interface HomeToolbarWeatherPresenter {
    companion object {
        const val REQUEST_CHECK_SETTINGS: Int = 101

        fun build(view: HomeToolbarWeatherView): HomeToolbarWeatherPresenter =
            HomeToolbarWeatherPresenterImpl(view)
    }

    fun launchLocationRetriever()
}