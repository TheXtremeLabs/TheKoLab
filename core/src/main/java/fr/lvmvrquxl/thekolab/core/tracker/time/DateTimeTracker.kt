package fr.lvmvrquxl.thekolab.core.tracker.time

import fr.lvmvrquxl.thekolab.core.tracker.Tracker
import kotlinx.coroutines.*
import java.text.DateFormat
import java.util.*

/**
 * Tracker for date and time.
 *
 * This class is responsible for tracking date and time of the running device.
 *
 * @param interval Interval between tracker's updating
 * @param listener Listener of date and time tracker
 *
 * @since 0.1.3
 *
 * @see [DateTimeTrackerListener]
 * @see [Tracker]
 */
internal abstract class DateTimeTracker(
    private val interval: Long,
    private val listener: DateTimeTrackerListener
) : Tracker() {
    /**
     * Format of date or time.
     *
     * This field should be an instance of date or an instance of time.
     *
     * @since 0.1.3
     *
     * @see [DateFormat.getDateInstance]
     * @see [DateFormat.getTimeInstance]
     */
    protected var dateFormat: DateFormat? = null

    override suspend fun start(): Job = super.coroutineScope.launch(Dispatchers.Default) {
        while (this.isActive) {
            val currentDateTime: String? = this@DateTimeTracker.getCurrentDateTime()
            currentDateTime?.let { value: String -> this@DateTimeTracker.listener.updateValue(value) }
            delay(this@DateTimeTracker.interval)
        }
    }

    private fun getCurrentDateTime(): String? = runBlocking(Dispatchers.Default) {
        val currentDate: Deferred<Date> = this.async { Calendar.getInstance().time }
        this@DateTimeTracker.dateFormat?.format(currentDate.await())
    }
}