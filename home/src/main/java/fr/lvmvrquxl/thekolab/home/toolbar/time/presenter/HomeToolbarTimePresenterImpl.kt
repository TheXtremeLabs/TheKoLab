package fr.lvmvrquxl.thekolab.home.toolbar.time.presenter

import fr.lvmvrquxl.thekolab.home.toolbar.time.presenter.tracker.Tracker
import fr.lvmvrquxl.thekolab.home.toolbar.time.presenter.tracker.TrackerBuilder
import kotlinx.coroutines.*
import java.util.*

/**
 * Presenter's implementation of the toolbar's time display.
 *
 * @param callback Callback with time updater
 *
 * @since 0.1.3
 * @see [HomeToolbarTimeCallback]
 */
internal class HomeToolbarTimePresenterImpl(private val callback: HomeToolbarTimeCallback) :
    HomeToolbarTimePresenter {
    private val trackers: List<Tracker> = TrackerBuilder()
        .withDate { date: String -> this.updateDate(date) }
        .withTime { time: String -> this.updateTime(time) }
        .build()

    override fun cancelCoroutines() = runBlocking(Dispatchers.Default) {
        this@HomeToolbarTimePresenterImpl.trackers.forEach { t: Tracker -> t.cancelCoroutines() }
    }

    override fun startBackgroundCoroutines() = runBlocking(Dispatchers.Default) {
        this@HomeToolbarTimePresenterImpl.trackers.forEach { t: Tracker -> t.start() }
    }

    private fun updateDate(date: String) = runBlocking(Dispatchers.Main) {
        this@HomeToolbarTimePresenterImpl.callback.updateDate(date)
    }

    private fun updateTime(time: String) = runBlocking(Dispatchers.Main) {
        this@HomeToolbarTimePresenterImpl.callback.updateTime(time)
    }
}