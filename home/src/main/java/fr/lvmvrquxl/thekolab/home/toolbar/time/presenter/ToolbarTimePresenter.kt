package fr.lvmvrquxl.thekolab.home.toolbar.time.presenter

import fr.lvmvrquxl.thekolab.core.tracker.TrackerBuilder
import fr.lvmvrquxl.thekolab.shared.presenter.Presenter
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
internal class ToolbarTimePresenter(private val callback: ToolbarTimeCallback) : Presenter() {
    companion object {
        fun create(callback: ToolbarTimeCallback): Presenter = ToolbarTimePresenter(callback)
    }

    init {
        super.trackers = TrackerBuilder()
            .withDate { date: String -> this.updateDate(date) }
            .withTime { time: String -> this.updateTime(time) }
            .build()
    }

    private fun updateDate(date: String) = runBlocking(Dispatchers.Main) {
        this@ToolbarTimePresenter.callback.updateDate(date)
    }

    private fun updateTime(time: String) = runBlocking(Dispatchers.Main) {
        this@ToolbarTimePresenter.callback.updateTime(time)
    }
}