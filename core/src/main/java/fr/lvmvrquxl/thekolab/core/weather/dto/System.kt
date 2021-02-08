package fr.lvmvrquxl.thekolab.core.weather.dto

import com.google.gson.annotations.Expose

/**
 * System data.
 *
 * @since 0.1.3
 */
data class System(
    /**
     * Country code.
     *
     * @since 0.1.3
     */
    @Expose val country: String,
    private val id: Int,
    private val sunrise: Long,
    private val sunset: Long,
    private val type: Int
)
