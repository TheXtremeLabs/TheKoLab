package fr.lvmvrquxl.thekolab.colors.model

import android.content.Context

internal interface IColors {
    companion object {
        fun create(context: Context): IColors = Colors.create(context)
    }

    val first: Color
    val random: Color
}