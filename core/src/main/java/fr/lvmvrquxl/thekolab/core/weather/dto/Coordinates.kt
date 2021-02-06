package fr.lvmvrquxl.thekolab.core.weather.dto

import com.google.gson.annotations.SerializedName

data class Coordinates(
    @SerializedName("lat") val latitude: Double,
    @SerializedName("lon") val longitude: Double
)
