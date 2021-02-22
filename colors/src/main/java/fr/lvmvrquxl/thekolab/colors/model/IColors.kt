package fr.lvmvrquxl.thekolab.colors.model

import android.content.Context
import fr.lvmvrquxl.thekolab.colors.model.color.Color

/**
 * Interface of the colors model.
 *
 * This interface should be used for retrieving colors to display.
 *
 * @since 1.0.0
 */
internal interface IColors {
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
         */
        fun create(context: Context): IColors = Colors.create(context)
    }

    /**
     * Retrieve default color.
     *
     * @since 1.0.0
     *
     * @see [Color]
     */
    val default: Color

    /**
     * Retrieve random color.
     *
     * @since 1.0.0
     *
     * @see [Color]
     */
    val random: Color
}