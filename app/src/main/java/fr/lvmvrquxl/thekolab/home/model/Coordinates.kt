package fr.lvmvrquxl.thekolab.home.model

import com.google.gson.annotations.SerializedName

internal data class Coordinates(
    @SerializedName("lat") internal val latitude: Double,
    @SerializedName("lon") internal val longitude: Double
)
