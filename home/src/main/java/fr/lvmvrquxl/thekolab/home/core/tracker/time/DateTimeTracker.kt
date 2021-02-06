package fr.lvmvrquxl.thekolab.home.core.tracker.time

import fr.lvmvrquxl.thekolab.home.core.tracker.Tracker
import kotlinx.coroutines.*
import java.text.DateFormat
import java.util.*

internal abstract class DateTimeTracker(
    private val interval: Long,
    private val listener: DateTimeTrackerListener
) : Tracker() {
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