package fr.lvmvrquxl.thekolab.core.weather.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Weather(
    @Expose val description: String,
    val icon: String,
    val id: Int,
    @SerializedName("main") val title: String
)
