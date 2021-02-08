package fr.lvmvrquxl.thekolab.core.weather.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Weather DTO.
 *
 * This class is a representation of the JSON data returns by OpenWeatherMap API.
 *
 * @since 0.1.3
 */
data class WeatherDTO(
    /**
     * City name.
     *
     * @since 0.1.3
     */
    @Expose @SerializedName("name") val cityName: String,
    /**
     * Main weather data.
     *
     * @since 0.1.3
     *
     * @see [MainData]
     */
    @Expose @SerializedName("main") val mainData: MainData,
    /**
     * System data.
     *
     * @since 0.1.3
     *
     * @see [System]
     */
    @Expose @SerializedName("sys") val system: System,
    /**
     * Weather info.
     *
     * @since 0.1.3
     *
     * @see [List]
     * @see [Weather]
     */
    @Expose val weather: List<Weather>,
    private val base: String,
    @SerializedName("id") private val cityId: Int,
    private val clouds: Clouds,
    @SerializedName("cod") private val code: String,
    @SerializedName("coor") private val coordinates: Coordinates,
    @SerializedName("dt") private val dataTime: Long,
    private val rain: Rain,
    private val timezone: Int,
    private val visibility: Int
)
