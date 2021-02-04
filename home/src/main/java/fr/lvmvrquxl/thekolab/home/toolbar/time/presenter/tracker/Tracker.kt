package fr.lvmvrquxl.thekolab.home.toolbar.time.presenter.tracker

import kotlinx.coroutines.*
import java.text.DateFormat
import java.util.*

internal abstract class Tracker(
    private val interval: Long,
    private val listener: TrackerListener
) {
    companion object {
        private const val SCOPE_NAME: String = "Tracker"
    }

    private val coroutineScope: CoroutineScope = CoroutineScope(CoroutineName(SCOPE_NAME))
    protected var dateFormat: DateFormat? = null

    suspend fun start(): Job = this.coroutineScope.launch {
        while (this.isActive) {
            val currentDateTime: String? = this@Tracker.getCurrentDateTime()
            currentDateTime?.let { value: String ->  this@Tracker.listener.updateValue(value) }
            delay(this@Tracker.interval)
        }
    }

    fun cancelCoroutines() = this.coroutineScope.cancel()

    private fun getCurrentDateTime(): String? = runBlocking(Dispatchers.Default) {
        val currentDate: Deferred<Date> = this.async { Calendar.getInstance().time }
        this@Tracker.dateFormat?.format(currentDate.await())
    }
}