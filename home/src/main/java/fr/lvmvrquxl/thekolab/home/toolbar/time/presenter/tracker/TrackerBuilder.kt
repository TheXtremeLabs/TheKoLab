package fr.lvmvrquxl.thekolab.home.toolbar.time.presenter.tracker

internal class TrackerBuilder {
    companion object {
        private const val ERROR_MSG: String = "No tracker was provided!"
    }

    private var dateTracker: DateTracker? = null
    private var timeTracker: TimeTracker? = null

    fun build(): List<Tracker> {
        val trackers: MutableList<Tracker> = mutableListOf()
        if (null != this.dateTracker) this.dateTracker?.let { t: DateTracker -> trackers.add(t) }
        if (null != this.timeTracker) this.timeTracker?.let { t: TimeTracker -> trackers.add(t) }
        if (trackers.isEmpty()) throw NoSuchElementException(ERROR_MSG)
        return trackers
    }

    fun withDate(dateListener: TrackerListener): TrackerBuilder {
        this.dateTracker = DateTracker(dateListener)
        return this
    }

    fun withTime(timeListener: TrackerListener): TrackerBuilder {
        this.timeTracker = TimeTracker(timeListener)
        return this
    }
}