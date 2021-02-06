package fr.lvmvrquxl.thekolab.core.tracker.time

import java.text.DateFormat

internal class DateTracker(listener: DateTimeTrackerListener) :
    DateTimeTracker(INTERVAL, listener) {
    companion object {
        private const val DATE_STYLE: Int = DateFormat.MEDIUM
        private const val INTERVAL: Long = 1000 * 60
    }

    init {
        super.dateFormat = DateFormat.getDateInstance(DATE_STYLE)
    }
}