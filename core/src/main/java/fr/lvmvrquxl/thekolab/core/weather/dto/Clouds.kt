package fr.lvmvrquxl.thekolab.core.weather.dto

import com.google.gson.annotations.SerializedName

data class Clouds(@SerializedName("all") val cloudiness: Int)
