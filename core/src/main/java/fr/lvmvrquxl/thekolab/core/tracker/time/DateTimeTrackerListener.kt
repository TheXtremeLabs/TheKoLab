package fr.lvmvrquxl.thekolab.core.tracker.time

/**
 * Listener of date and time trackers.
 *
 * @since 1.0.0
 *
 * @see [DateTracker]
 * @see [TimeTracker]
 */
fun interface DateTimeTrackerListener {
    /**
     * Update value from tracker.
     *
     * @param value New value
     *
     * @since 1.0.0
     */
    fun updateValue(value: String)
}