package fr.lvmvrquxl.thekolab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import fr.lvmvrquxl.thekolab.databinding.HomeToolbarDateTimeBinding
import kotlinx.coroutines.*
import java.text.DateFormat
import java.util.*

class ToolbarTimeFragment : Fragment(VIEW) {
    companion object {
        private const val DATE_STYLE: Int = DateFormat.MEDIUM
        private const val ONE_SECOND: Long = 1000
        private const val SCOPE: String = "ToolbarTimeScope"
        private const val TIME_STYLE: Int = DateFormat.SHORT
        private const val VIEW: Int = R.layout.home_toolbar_date_time
    }

    private val toolbarTimeScope: CoroutineScope = CoroutineScope(CoroutineName(SCOPE))
    private var timeUpdaterJob: Job? = null
    private var viewBinding: HomeToolbarDateTimeBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val attachToParent = false
        this.viewBinding = HomeToolbarDateTimeBinding.inflate(inflater, container, attachToParent)
        return this.viewBinding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        this.toolbarTimeScope.cancel()
        this.viewBinding = null
    }

    override fun onPause() {
        super.onPause()
        this.timeUpdaterJob?.cancel()
    }

    override fun onResume() {
        super.onResume()
        this.launchTimeUpdater()
        this.initDateText()
    }

    private fun getCurrentTime(): String {
        val currentDate: Date = Calendar.getInstance().time
        val dateFormat: DateFormat = DateFormat.getTimeInstance(TIME_STYLE)
        return dateFormat.format(currentDate)
    }

    private fun initDateText() {
        val currentDate: Date = Calendar.getInstance().time
        val dateFormat: DateFormat = DateFormat.getDateInstance(DATE_STYLE)
        val dateText: String = dateFormat.format(currentDate)
        this.viewBinding?.toolbarDate?.text = dateText
    }

    private fun launchTimeUpdater() {
        this.timeUpdaterJob = this.toolbarTimeScope.launch {
            while (this.isActive) {
                val timeText: String = this@ToolbarTimeFragment.getCurrentTime()
                val updateTimeTextJob: Job = this@ToolbarTimeFragment.updateTimeText(timeText)
                updateTimeTextJob.join()
                delay(ONE_SECOND)
            }
        }
    }

    private fun updateTimeText(timeText: String): Job {
        return this.toolbarTimeScope.launch(Dispatchers.Main) {
            this@ToolbarTimeFragment.viewBinding?.toolbarTime?.text = timeText
        }
    }
}