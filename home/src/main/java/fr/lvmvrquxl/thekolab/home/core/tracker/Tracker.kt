package fr.lvmvrquxl.thekolab.home.core.tracker

import kotlinx.coroutines.Job

internal interface Tracker {
    suspend fun start(): Job

    fun cancelCoroutines()
}