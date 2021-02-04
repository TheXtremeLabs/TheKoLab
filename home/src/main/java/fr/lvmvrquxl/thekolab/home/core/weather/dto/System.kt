package fr.lvmvrquxl.thekolab.home.core.weather.dto

import com.google.gson.annotations.Expose

internal data class System(
    @Expose val country: String,
    val id: Int,
    val sunrise: Long,
    val sunset: Long,
    val type: Int
)
