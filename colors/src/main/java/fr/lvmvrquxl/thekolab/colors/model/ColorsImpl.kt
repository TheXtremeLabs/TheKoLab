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
 */
internal class ColorsImpl private constructor(private val context: Context) : Colors {
    companion object {
        /**
         * Create a new instance of colors model.
         *
         * @param context Context for building colors
         *
         * @return New instance of colors model
         */
        fun create(context: Context): Colors = ColorsImpl(context)
    }

    private val colors: List<Color> = listOf(
        White.create(this.context),
        Orange.create(this.context),
        Purple.create(this.context),
        Blue.create(this.context),
        Red.create(this.context)
    )

    override suspend fun default(): Color = this.colors.first()

    override suspend fun random(): Color = this.colors.random()
}