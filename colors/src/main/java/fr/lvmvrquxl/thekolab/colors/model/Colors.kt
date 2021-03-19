package fr.lvmvrquxl.thekolab.colors.model

import android.content.Context
import fr.lvmvrquxl.thekolab.colors.model.color.Color

/**
 * Interface of the colors model.
 *
 * This interface should be used for retrieving colors to display.
 */
internal interface Colors {
    companion object {
        /**
         * Create a new instance of colors model.
         *
         * @param context Context for building colors
         *
         * @return New instance of colors model
         */
        fun create(context: Context): Colors = ColorsImpl.create(context)
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