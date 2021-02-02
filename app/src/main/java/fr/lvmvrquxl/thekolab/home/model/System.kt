package fr.lvmvrquxl.thekolab.home.model

internal data class System(
    internal val country: String,
    internal val id: Int,
    internal val sunrise: Long,
    internal val sunset: Long,
    internal val type: Int
)
