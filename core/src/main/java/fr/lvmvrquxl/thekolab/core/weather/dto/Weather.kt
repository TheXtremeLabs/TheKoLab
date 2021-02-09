package fr.lvmvrquxl.thekolab.core.weather.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Weather info.
 *
 * @since 1.0.0
 */
data class Weather(
    /**
     * Weather condition.
     *
     * @since 1.0.0
     */
    @Expose @SerializedName("description") val condition: String,
    private val icon: String,
    private val id: Int,
    @SerializedName("main") private val title: String
)
