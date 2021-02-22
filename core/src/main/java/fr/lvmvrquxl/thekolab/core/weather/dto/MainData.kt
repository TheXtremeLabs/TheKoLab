package fr.lvmvrquxl.thekolab.core.weather.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Main weather data.
 *
 * @since 1.0.0
 */
data class MainData(
    /**
     * Current temperature.
     *
     * Supported units:
     * - Kelvin (default)
     * - Celsius (metric)
     * - Fahrenheit (imperial).
     *
     * @since 1.0.0
     */
    @Expose @SerializedName("temp") val temperature: Double,
    @SerializedName("feels_like") private val feelsLike: Double,
    private val humidity: Int,
    @SerializedName("temp_max") private val maxTemperature: Double,
    @SerializedName("temp_min") private val minTemperature: Double,
    private val pressure: Int
)
