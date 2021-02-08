package fr.lvmvrquxl.thekolab.core.tracker

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel

/**
 * Base class of all trackers.
 *
 * @since 0.1.3
 */
abstract class Tracker {
    companion object {
        private const val SCOPE_NAME: String = "Tracker"
    }

    /**
     * Coroutine's scope of the tracker.
     *
     * @since 0.1.3
     *
     * @see [CoroutineName]
     * @see [CoroutineScope]
     */
    protected val coroutineScope: CoroutineScope = CoroutineScope(CoroutineName(SCOPE_NAME))

    /**
     * Start the tracker in its coroutine's scope.
     *
     * @return Job of the coroutine that starts the tracker
     *
     * @since 0.1.3
     *
     * @see [coroutineScope]
     * @see [Job]
     */
    abstract suspend fun start(): Job

    /**
     * Cancel all coroutines of the tracker.
     *
     * @since 0.1.3
     *
     * @see [coroutineScope]
     */
    fun cancelCoroutines() = this.coroutineScope.cancel()
}