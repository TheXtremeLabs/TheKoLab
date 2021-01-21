package fr.lvmvrquxl.thekolab.home.presenter

import fr.lvmvrquxl.thekolab.home.view.HomeToolbarTimeView

interface HomeToolbarTimePresenter {
    companion object {
        fun build(view: HomeToolbarTimeView): HomeToolbarTimePresenter =
            HomeToolbarTimePresenterImpl(view)
    }

    fun cancelCoroutineScope()

    fun cancelTimeUpdaterJob(): Unit?

    fun getCurrentDate(): String

    fun launchTimeUpdater()
}