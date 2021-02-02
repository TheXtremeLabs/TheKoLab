package fr.lvmvrquxl.thekolab.home.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

internal data class WeatherDTO(
    @SerializedName("coor") internal val coordinates: Coordinates,
    @Expose internal val weather: List<Weather>,
    internal val base: String,
    @Expose @SerializedName("main") internal val mainData: MainData,
    internal val visibility: Int,
    internal val rain: Rain,
    internal val clouds: Clouds,
    @SerializedName("dt") internal val dataTime: Long,
    @SerializedName("sys") internal val system: System,
    internal val timezone: Int,
    internal val id: Int,
    internal val name: String,
    @SerializedName("cod") internal val code: String
)
