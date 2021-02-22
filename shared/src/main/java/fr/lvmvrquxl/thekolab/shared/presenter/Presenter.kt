package fr.lvmvrquxl.thekolab.shared.presenter

import androidx.annotation.CallSuper
import fr.lvmvrquxl.thekolab.core.tracker.Tracker
import kotlinx.coroutines.*

/**
 * Parent of all presenters.
 *
 * @since 1.0.0
 */
abstract class Presenter {
    companion object {
        private const val SCOPE_NAME: String = "Presenter"
    }

    /**
     * Scope of the coroutines.
     *
     * @since 1.0.0
     *
     * @see [CoroutineName]
     * @see [CoroutineScope]
     */
    protected val coroutineScope: CoroutineScope = CoroutineScope(CoroutineName(SCOPE_NAME))

    /**
     * Trackers to use.
     *
     * @since 1.0.0
     *
     * @see [List]
     * @see [Tracker]
     */
    protected var trackers: List<Tracker> = listOf()

    /**
     * Cancel all coroutines in the presenter's scope.
     *
     * @since 1.0.0
     */
    @CallSuper
    fun cancelCoroutines() = runBlocking(Dispatchers.Default) {
        this@Presenter.trackers.forEach { t: Tracker -> t.cancelCoroutines() }
        this@Presenter.coroutineScope.cancel()
    }

    /**
     * Launch background coroutines.
     *
     * @since 1.0.0
     */
    fun startBackgroundCoroutines() = runBlocking(Dispatchers.Default) {
        this@Presenter.trackers.forEach { t: Tracker -> t.start() }
    }
}