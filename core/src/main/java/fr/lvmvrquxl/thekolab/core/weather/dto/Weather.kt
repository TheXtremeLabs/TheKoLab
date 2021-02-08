package fr.lvmvrquxl.thekolab.core.weather.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Weather info.
 *
 * @since 0.1.3
 */
data class Weather(
    /**
     * Weather condition.
     *
     * @since 0.1.3
     */
    @Expose @SerializedName("description") val condition: String,
    private val icon: String,
    private val id: Int,
    @SerializedName("main") private val title: String
)
