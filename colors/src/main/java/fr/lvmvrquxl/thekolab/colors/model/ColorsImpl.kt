package fr.lvmvrquxl.thekolab.colors.model

import fr.lvmvrquxl.thekolab.colors.model.color.*
import fr.lvmvrquxl.thekolab.shared.activity.ActivityReference

/**
 * Implementation of the colors model.
 */
internal class ColorsImpl private constructor(private val activityReference: ActivityReference) :
    Colors {
    companion object {
        /**
         * Create a new instance of colors model.
         *
         * @param activityReference Reference of the colors activity
         *
         * @return New instance of colors model
         */
        fun create(activityReference: ActivityReference): Colors = ColorsImpl(activityReference)
    }

    private val colors: List<Color> = listOf(
        White.create(this.activityReference),
        Orange.create(this.activityReference),
        Purple.create(this.activityReference),
        Blue.create(this.activityReference),
        Red.create(this.activityReference)
    )

    override suspend fun default(): Color = this.colors.first()

    override suspend fun random(): Color = this.colors.random()
}