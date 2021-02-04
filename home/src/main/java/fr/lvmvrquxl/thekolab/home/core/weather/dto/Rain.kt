package fr.lvmvrquxl.thekolab.home.core.weather.dto

import com.google.gson.annotations.SerializedName

internal data class Rain(
    @SerializedName("1h") val lastHour: Double,
    @SerializedName("3h") val lastThreeHour: Double
)
