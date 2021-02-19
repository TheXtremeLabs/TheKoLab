package fr.lvmvrquxl.thekolab.colors.model

import android.content.Context
import fr.lvmvrquxl.thekolab.colors.model.color.*
import fr.lvmvrquxl.thekolab.colors.model.color.Blue
import fr.lvmvrquxl.thekolab.colors.model.color.Color
import fr.lvmvrquxl.thekolab.colors.model.color.Purple
import fr.lvmvrquxl.thekolab.colors.model.color.Red
import fr.lvmvrquxl.thekolab.colors.model.color.White

/**
 * Implementation of the colors model.
 *
 * @param context Context for building colors
 *
 * @since 1.0.0
 *
 * @see [Context]
 * @see [IColors]
 */
internal class Colors private constructor(private val context: Context) : IColors {
    companion object {
        /**
         * Create a new instance of colors model.
         *
         * @param context Context for building colors
         *
         * @return New instance of colors model
         *
         * @since 1.0.0
         *
         * @see [Context]
         * @see [IColors]
         */
        fun create(context: Context): IColors = Colors(context)
    }

    override val default: Color
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