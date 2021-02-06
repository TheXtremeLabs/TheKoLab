package fr.lvmvrquxl.thekolab.home.toolbar.time.presenter

import fr.lvmvrquxl.thekolab.home.core.tracker.Tracker
import fr.lvmvrquxl.thekolab.home.core.tracker.TrackerBuilder
import kotlinx.coroutines.*
import java.util.*

/**
 * Presenter's implementation of the toolbar's time display.
 *
 * @param callback Callback with time updater
 *
 * @since 0.1.3
 * @see [ToolbarTimeCallback]
 */
internal class ToolbarTimePresenterImpl(private val callback: ToolbarTimeCallback) :
    ToolbarTimePresenter {
    private val trackers: List<Tracker> = TrackerBuilder()
        .withDate { date: String -> this.updateDate(date) }
        .withTime { time: String -> this.updateTime(time) }
        .build()

    override fun cancelCoroutines() = runBlocking(Dispatchers.Default) {
        this@ToolbarTimePresenterImpl.trackers.forEach { t: Tracker -> t.cancelCoroutines() }
    }

    override fun startBackgroundCoroutines() = runBlocking(Dispatchers.Default) {
        this@ToolbarTimePresenterImpl.trackers.forEach { t: Tracker -> t.start() }
    }

    private fun updateDate(date: String) = runBlocking(Dispatchers.Main) {
        this@ToolbarTimePresenterImpl.callback.updateDate(date)
    }

    private fun updateTime(time: String) = runBlocking(Dispatchers.Main) {
        this@ToolbarTimePresenterImpl.callback.updateTime(time)
    }
}