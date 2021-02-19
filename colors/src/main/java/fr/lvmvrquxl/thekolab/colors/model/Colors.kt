package fr.lvmvrquxl.thekolab.colors.model

import android.content.Context
import fr.lvmvrquxl.thekolab.colors.model.color.*
import fr.lvmvrquxl.thekolab.colors.model.color.Blue
import fr.lvmvrquxl.thekolab.colors.model.color.Color
import fr.lvmvrquxl.thekolab.colors.model.color.Purple
import fr.lvmvrquxl.thekolab.colors.model.color.Red
import fr.lvmvrquxl.thekolab.colors.model.color.White

internal class Colors private constructor(private val context: Context) : IColors {
    companion object {
        fun create(context: Context): IColors = Colors(context)
    }

    override val first: Color
        get() = this.colors.first()
    override val random: Color
        get() = this.colors.random()

    private val colors: List<Color> = listOf(
        White.create(this.context),
        Orange.create(this.context),
        Purple.create(this.context),
        Blue.create(this.context),
        Red.create(this.context)
    )
}