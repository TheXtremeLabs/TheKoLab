package fr.lvmvrquxl.thekolab.home.core.tracker

import kotlinx.coroutines.*
import java.text.DateFormat
import java.util.*

internal abstract class DateTimeTracker(
    private val interval: Long,
    private val listener: TrackerListener
) : Tracker {
    companion object {
        private const val SCOPE_NAME: String = "DateTimeTracker"
    }

    private val coroutineScope: CoroutineScope = CoroutineScope(CoroutineName(SCOPE_NAME))
    protected var dateFormat: DateFormat? = null

    override suspend fun start(): Job = this.coroutineScope.launch(Dispatchers.Default) {
        while (this.isActive) {
            val currentDateTime: String? = this@DateTimeTracker.getCurrentDateTime()
            currentDateTime?.let { value: String -> this@DateTimeTracker.listener.updateValue(value) }
            delay(this@DateTimeTracker.interval)
        }
    }

    override fun cancelCoroutines() = this.coroutineScope.cancel()

    private fun getCurrentDateTime(): String? = runBlocking(Dispatchers.Default) {
        val currentDate: Deferred<Date> = this.async { Calendar.getInstance().time }
        this@DateTimeTracker.dateFormat?.format(currentDate.await())
    }
}