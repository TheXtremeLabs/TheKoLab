package fr.lvmvrquxl.thekolab.core.tracker

import fr.lvmvrquxl.thekolab.core.tracker.time.DateTimeTrackerListener
import fr.lvmvrquxl.thekolab.core.tracker.time.DateTracker
import fr.lvmvrquxl.thekolab.core.tracker.time.TimeTracker
import fr.lvmvrquxl.thekolab.core.tracker.weather.WeatherTracker
import fr.lvmvrquxl.thekolab.core.tracker.weather.WeatherTrackerListener

class TrackerBuilder {
    companion object {
        private const val ERROR_MSG: String = "No tracker was provided!"
    }

    private var dateTracker: DateTracker? = null
    private var timeTracker: TimeTracker? = null
    private var weatherTracker: WeatherTracker? = null

    fun build(): List<Tracker> {
        val trackers: MutableList<Tracker> = mutableListOf()
        this.dateTracker?.let { t: DateTracker -> trackers.add(t) }
        this.timeTracker?.let { t: TimeTracker -> trackers.add(t) }
        this.weatherTracker?.let { t: WeatherTracker -> trackers.add(t) }
        if (trackers.isEmpty()) throw NoSuchElementException(ERROR_MSG)
        return trackers
    }

    fun withDate(dateListener: DateTimeTrackerListener): TrackerBuilder {
        this.dateTracker = DateTracker(dateListener)
        return this
    }

    fun withTime(timeListener: DateTimeTrackerListener): TrackerBuilder {
        this.timeTracker = TimeTracker(timeListener)
        return this
    }

    fun withWeather(weatherListener: WeatherTrackerListener): TrackerBuilder {
        this.weatherTracker = WeatherTracker(weatherListener)
        return this
    }
}