package fr.lvmvrquxl.thekolab.home.toolbar.time.presenter

import fr.lvmvrquxl.thekolab.home.toolbar.time.view.HomeToolbarTimeView
import kotlinx.coroutines.*
import java.text.DateFormat
import java.util.*

/**
 * Presenter's implementation of the toolbar's time display.
 *
 * @param view View of the toolbar's time display
 *
 * @since 0.1.3
 * @see [HomeToolbarTimeView]
 */
class HomeToolbarTimePresenterImpl(private val view: HomeToolbarTimeView) :
    HomeToolbarTimePresenter {
    companion object {
        private const val DATE_STYLE: Int = DateFormat.MEDIUM
        private const val ONE_SECOND: Long = 1000
        private const val SCOPE_NAME: String = "ToolbarTimeScope"
        private const val TIME_STYLE: Int = DateFormat.SHORT
    }

    private val coroutineScope: CoroutineScope = CoroutineScope(CoroutineName(SCOPE_NAME))
    private var timeUpdaterJob: Job? = null

    override fun cancelCoroutineScope() = this.coroutineScope.cancel()

    override fun cancelTimeUpdaterJob(): Unit? = this.timeUpdaterJob?.cancel()

    override fun getCurrentDate(): String { // TODO: use a coroutine
        val currentDate: Date = Calendar.getInstance().time
        val dateFormat: DateFormat = DateFormat.getDateInstance(DATE_STYLE)
        return dateFormat.format(currentDate)
    }

    override fun launchTimeUpdater() {
        this.timeUpdaterJob = this.coroutineScope.launch {
            while (this.isActive) {
                val timeText: String = this@HomeToolbarTimePresenterImpl.getCurrentTime()
                val updateTimeViewJob: Job =
                    this@HomeToolbarTimePresenterImpl.updateTimeView(timeText)
                updateTimeViewJob.join()
                delay(ONE_SECOND)
            }
        }
    }

    private fun getCurrentTime(): String { // TODO: use a coroutine
        val currentTime: Date = Calendar.getInstance().time
        val dateFormat: DateFormat = DateFormat.getTimeInstance(TIME_STYLE)
        return dateFormat.format(currentTime)
    }

    private fun updateTimeView(timeText: String): Job =
        this.coroutineScope.launch(Dispatchers.Main) {
            this@HomeToolbarTimePresenterImpl.view.updateTimeText(timeText)
        }
}