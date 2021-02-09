package fr.lvmvrquxl.thekolab.core.weather.dto

import com.google.gson.annotations.Expose

/**
 * System data.
 *
 * @since 1.0.0
 */
data class System(
    /**
     * Country code.
     *
     * @since 1.0.0
     */
    @Expose val country: String,
    private val id: Int,
    private val sunrise: Long,
    private val sunset: Long,
    private val type: Int
)
