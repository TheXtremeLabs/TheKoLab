package fr.lvmvrquxl.thekolab.core.weather.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class WeatherDTO(
    val base: String,
    @SerializedName("id") val cityId: Int,
    @Expose @SerializedName("name") val cityName: String,
    val clouds: Clouds,
    @SerializedName("cod") val code: String,
    @SerializedName("coor") val coordinates: Coordinates,
    @SerializedName("dt") val dataTime: Long,
    @Expose @SerializedName("main") val mainData: MainData,
    val rain: Rain,
    @Expose @SerializedName("sys") val system: System,
    val timezone: Int,
    val visibility: Int,
    @Expose val weather: List<Weather>
)
