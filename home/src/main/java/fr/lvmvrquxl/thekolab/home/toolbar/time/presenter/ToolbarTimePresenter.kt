package fr.lvmvrquxl.thekolab.home.toolbar.time.presenter

import fr.lvmvrquxl.thekolab.core.tracker.TrackerBuilder
import fr.lvmvrquxl.thekolab.shared.presenter.Presenter
import kotlinx.coroutines.*
import java.util.*

/**
 * Presenter of the toolbar's time display.
 *
 * @param callback Toolbar's time callback
 *
 * @since 1.0.0
 *
 * @see [Presenter]
 * @see [ToolbarTimeCallback]
 */
internal class ToolbarTimePresenter private constructor(private val callback: ToolbarTimeCallback) :
    Presenter() {
    companion object {
        /**
         * Create an instance of toolbar's time presenter.
         *
         * @param callback Toolbar's time callback
         *
         * @return New instance of toolbar's time presenter
         *
         * @since 1.0.0
         *
         * @see [Presenter]
         * @see [ToolbarTimeCallback]
         */
        fun create(callback: ToolbarTimeCallback): Presenter = ToolbarTimePresenter(callback)
    }

    init {
        super.trackers = TrackerBuilder.create()
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