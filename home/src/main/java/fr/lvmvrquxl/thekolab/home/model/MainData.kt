package fr.lvmvrquxl.thekolab.home.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

internal data class MainData(
    @SerializedName("feels_like") internal val feelsLike: Double,
    internal val humidity: Int,
    @SerializedName("temp_max") internal val maxTemperature: Double,
    @SerializedName("temp_min") internal val minTemperature: Double,
    internal val pressure: Int,
    @Expose @SerializedName("temp") internal val temperature: Double
)
