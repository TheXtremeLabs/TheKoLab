package fr.lvmvrquxl.thekolab.home.toolbar.time.presenter.tracker

import java.text.DateFormat

internal class TimeTracker(listener: TrackerListener) : Tracker(INTERVAL, listener) {
    companion object {
        private const val INTERVAL: Long = 1000
        private const val TIME_STYLE: Int = DateFormat.SHORT
    }

    init {
        super.dateFormat = DateFormat.getTimeInstance(TIME_STYLE)
    }
}