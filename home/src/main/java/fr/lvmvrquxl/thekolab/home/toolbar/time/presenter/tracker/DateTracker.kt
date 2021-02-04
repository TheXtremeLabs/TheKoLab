package fr.lvmvrquxl.thekolab.home.toolbar.time.presenter.tracker

import java.text.DateFormat

internal class DateTracker(listener: TrackerListener) : Tracker(INTERVAL, listener) {
    companion object {
        private const val DATE_STYLE: Int = DateFormat.MEDIUM
        private const val INTERVAL: Long = 1000 * 60
    }

    init {
        super.dateFormat = DateFormat.getDateInstance(DATE_STYLE)
    }
}