package fr.lvmvrquxl.thekolab.home.core.weather.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

internal data class Weather(
    @Expose val description: String,
    val icon: String,
    val id: Int,
    @SerializedName("main") val title: String
)
