package fr.lvmvrquxl.thekolab.home.core.weather.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

internal data class MainData(
    @SerializedName("feels_like") val feelsLike: Double,
    val humidity: Int,
    @SerializedName("temp_max") val maxTemperature: Double,
    @SerializedName("temp_min") val minTemperature: Double,
    val pressure: Int,
    @Expose @SerializedName("temp") val temperature: Double
)
