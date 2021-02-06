package fr.lvmvrquxl.thekolab.core.weather.dto

import com.google.gson.annotations.Expose

data class System(
    @Expose val country: String,
    val id: Int,
    val sunrise: Long,
    val sunset: Long,
    val type: Int
)
