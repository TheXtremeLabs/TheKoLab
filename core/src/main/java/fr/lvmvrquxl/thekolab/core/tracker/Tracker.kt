package fr.lvmvrquxl.thekolab.core.tracker

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel

abstract class Tracker {
    companion object {
        private const val SCOPE_NAME: String = "Tracker"
    }

    protected val coroutineScope: CoroutineScope = CoroutineScope(CoroutineName(SCOPE_NAME))

    abstract suspend fun start(): Job

    fun cancelCoroutines() = this.coroutineScope.cancel()
}