package fr.lvmvrquxl.thekolab.home.core.weather.dto

import com.google.gson.annotations.SerializedName

internal data class Coordinates(
    @SerializedName("lat") val latitude: Double,
    @SerializedName("lon") val longitude: Double
)
