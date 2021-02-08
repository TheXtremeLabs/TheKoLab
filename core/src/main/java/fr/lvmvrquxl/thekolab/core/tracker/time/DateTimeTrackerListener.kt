package fr.lvmvrquxl.thekolab.core.tracker.time

/**
 * Listener of date and time trackers.
 *
 * @since 0.1.3
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
     * @since 0.1.3
     */
    fun updateValue(value: String)
}