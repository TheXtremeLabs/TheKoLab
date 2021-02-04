package fr.lvmvrquxl.thekolab.home.core.weather.dto

import com.google.gson.annotations.SerializedName

internal data class Clouds(@SerializedName("all") val cloudiness: Int)
