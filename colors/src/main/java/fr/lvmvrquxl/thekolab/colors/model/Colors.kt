package fr.lvmvrquxl.thekolab.colors.model

import android.content.Context

internal class Colors private constructor(private val context: Context) {
    companion object {
        fun create(context: Context): Colors = Colors(context)
    }

    val first: Color
        get() = this.colors.first()
    val random: Color
        get() = this.colors.random()

    private val colors: List<Color> = listOf(
        White.create(this.context),
        Orange.create(this.context),
        Purple.create(this.context),
        Blue.create(this.context),
        Red.create(this.context)
    )
}