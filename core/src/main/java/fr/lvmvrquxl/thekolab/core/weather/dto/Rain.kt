package fr.lvmvrquxl.thekolab.core.weather.dto

import com.google.gson.annotations.SerializedName

data class Rain(
    @SerializedName("1h") private val lastHour: Double,
    @SerializedName("3h") private val lastThreeHour: Double
)
