package fr.lvmvrquxl.thekolab.core.tracker.time

import java.text.DateFormat

/**
 * Time tracker.
 *
 * @param listener Listener of the time tracker
 *
 * @since 1.0.0
 *
 * @see [DateTimeTracker]
 * @see [DateTimeTrackerListener]
 */
internal class TimeTracker(listener: DateTimeTrackerListener) :
    DateTimeTracker(INTERVAL, listener) {
    companion object {
        private const val INTERVAL: Long = 1000
        private const val TIME_STYLE: Int = DateFormat.SHORT
    }

    init {
        super.dateFormat = DateFormat.getTimeInstance(TIME_STYLE)
    }
}