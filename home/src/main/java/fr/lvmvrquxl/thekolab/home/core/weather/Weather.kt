package fr.lvmvrquxl.thekolab.home.core.weather

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

internal data class Weather(
    @Expose internal val description: String,
    internal val icon: String,
    internal val id: Int,
    @SerializedName("main") internal val title: String
)
