package fr.lvmvrquxl.thekolab.home.core.tracker

internal fun interface TrackerListener {
    fun updateValue(value: String)
}