package fr.lvmvrquxl.thekolab.core.tracker

import fr.lvmvrquxl.thekolab.core.tracker.time.DateTimeTrackerListener
import fr.lvmvrquxl.thekolab.core.tracker.time.DateTracker
import fr.lvmvrquxl.thekolab.core.tracker.time.TimeTracker
import fr.lvmvrquxl.thekolab.core.tracker.weather.WeatherTracker
import fr.lvmvrquxl.thekolab.core.tracker.weather.WeatherTrackerListener

/**
 * Builder of all trackers.
 *
 * This class should be used as an interface to build all trackers.
 *
 * @since 1.0.0
 */
class TrackerBuilder private constructor() {
    companion object {
        private const val ERROR_MSG: String = "No tracker was provided!"

        /**
         * Create an instance of the builder.
         *
         * @return New instance of the builder
         *
         * @since 1.0.0
         */
        fun create(): TrackerBuilder = TrackerBuilder()
    }

    private var dateTracker: DateTracker? = null
    private var timeTracker: TimeTracker? = null
    private var weatherTracker: WeatherTracker? = null

    /**
     * Build provided trackers.
     *
     * @return List of provided trackers
     *
     * @throws [NoSuchElementException] When no tracker was provided
     *
     * @since 1.0.0
     *
     * @see [List]
     * @see [NoSuchElementException]
     * @see [Tracker]
     */
    fun build(): List<Tracker> {
        val trackers: MutableList<Tracker> = mutableListOf()
        this.dateTracker?.let { t: DateTracker -> trackers.add(t) }
        this.timeTracker?.let { t: TimeTracker -> trackers.add(t) }
        this.weatherTracker?.let { t: WeatherTracker -> trackers.add(t) }
        if (trackers.isEmpty()) throw NoSuchElementException(ERROR_MSG)
        return trackers
    }

    /**
     * Provide a date tracker.
     *
     * @param dateListener Listener of the date tracker
     *
     * @return Current instance of the builder
     *
     * @since 1.0.0
     *
     * @see [DateTimeTrackerListener]
     * @see [TrackerBuilder]
     */
    fun withDate(dateListener: DateTimeTrackerListener): TrackerBuilder {
        this.dateTracker = DateTracker(dateListener)
        return this
    }

    /**
     * Provide a time tracker.
     *
     * @param timeListener Listener of the time tracker
     *
     * @return Current instance of the builder
     *
     * @since 1.0.0
     *
     * @see [DateTimeTrackerListener]
     * @see [TrackerBuilder]
     */
    fun withTime(timeListener: DateTimeTrackerListener): TrackerBuilder {
        this.timeTracker = TimeTracker(timeListener)
        return this
    }

    /**
     * Provide a weather tracker.
     *
     * @param weatherListener Listener of the weather tracker
     *
     * @return Current instance of the builder
     *
     * @since 1.0.0
     *
     * @see [TrackerBuilder]
     * @see [WeatherTrackerListener]
     */
    fun withWeather(weatherListener: WeatherTrackerListener): TrackerBuilder {
        this.weatherTracker = WeatherTracker(weatherListener)
        return this
    }
}