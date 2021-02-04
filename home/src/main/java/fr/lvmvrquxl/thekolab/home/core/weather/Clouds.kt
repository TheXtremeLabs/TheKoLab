package fr.lvmvrquxl.thekolab.home.core.weather

import com.google.gson.annotations.SerializedName

internal data class Clouds(@SerializedName("all") internal val cloudiness: Int)
