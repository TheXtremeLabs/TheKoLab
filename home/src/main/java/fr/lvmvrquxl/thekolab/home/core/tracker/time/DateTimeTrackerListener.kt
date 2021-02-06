package fr.lvmvrquxl.thekolab.home.core.tracker.time

internal fun interface DateTimeTrackerListener {
    fun updateValue(value: String)
}