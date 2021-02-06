package fr.lvmvrquxl.thekolab.home.core.tracker

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel

internal abstract class Tracker {
    companion object {
        private const val SCOPE_NAME: String = "Tracker"
    }

    protected val coroutineScope: CoroutineScope = CoroutineScope(CoroutineName(SCOPE_NAME))

    abstract suspend fun start(): Job

    fun cancelCoroutines() = this.coroutineScope.cancel()
}