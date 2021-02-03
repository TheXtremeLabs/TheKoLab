package fr.lvmvrquxl.thekolab.home.model

import com.google.gson.annotations.Expose

internal data class System(
    @Expose internal val country: String,
    internal val id: Int,
    internal val sunrise: Long,
    internal val sunset: Long,
    internal val type: Int
)
