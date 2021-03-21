package fr.lvmvrquxl.thekolab.colors.model

import fr.lvmvrquxl.thekolab.colors.model.color.Color
import fr.lvmvrquxl.thekolab.shared.activity.ActivityReference

/**
 * Interface of the colors model.
 */
internal interface Colors {
    companion object {
        /**
         * Create a new instance of colors model.
         *
         * @param activityReference Reference of the colors activity
         *
         * @return New instance of colors model
         */
        fun create(activityReference: ActivityReference): Colors =
            ColorsImpl.create(activityReference)
    }

    /**
     * Retrieve default color.
     *
     * @return Default color
     */
    suspend fun default(): Color

    /**
     * Get a random color.
     *
     * @return Random color
     */
    suspend fun random(): Color
}