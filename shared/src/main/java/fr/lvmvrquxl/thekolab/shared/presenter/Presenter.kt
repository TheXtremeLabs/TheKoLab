package fr.lvmvrquxl.thekolab.shared.presenter

import androidx.annotation.CallSuper
import fr.lvmvrquxl.thekolab.core.tracker.Tracker
import kotlinx.coroutines.*

abstract class Presenter {
    companion object {
        private const val SCOPE_NAME: String = "Presenter"
    }

    protected val coroutineScope: CoroutineScope = CoroutineScope(CoroutineName(SCOPE_NAME))
    protected var trackers: List<Tracker> = listOf()

    /**
     * Cancel all coroutines in the presenter's scope.
     *
     * This method cancels all running coroutines in the presenter's scope.
     *
     * @since 0.1.3
     */
    @CallSuper
    fun cancelCoroutines() = runBlocking(Dispatchers.Default) {
        this@Presenter.trackers.forEach { t: Tracker -> t.cancelCoroutines() }
        this@Presenter.coroutineScope.cancel()
    }

    /**
     * Launch background coroutines.
     *
     * @since 0.1.3
     */
    fun startBackgroundCoroutines() = runBlocking(Dispatchers.Default) {
        this@Presenter.trackers.forEach { t: Tracker -> t.start() }
    }
}