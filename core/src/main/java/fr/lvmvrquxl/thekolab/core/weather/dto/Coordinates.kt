package fr.lvmvrquxl.thekolab.core.weather.dto

import com.google.gson.annotations.SerializedName

data class Coordinates(
    @SerializedName("lat") private val latitude: Double,
    @SerializedName("lon") private val longitude: Double
)
