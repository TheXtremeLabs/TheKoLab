package fr.lvmvrquxl.thekolab.home.model

import com.google.gson.annotations.SerializedName

internal data class Rain(
    @SerializedName("1h") internal val lastHour: Double,
    @SerializedName("3h") internal val lastThreeHour: Double
)
