package fr.lvmvrquxl.thekolab.colors.model.color

import android.content.Context
import fr.lvmvrquxl.thekolab.colors.utils.ColorUtils
import fr.lvmvrquxl.thekolab.colors.utils.StringUtils

/**
 * Blue color.
 *
 * @param context Context for building color
 *
 * @since 1.0.0
 *
 * @see [Color]
 * @see [Context]
 */
internal class Blue private constructor(context: Context) :
    Color(StringUtils.blue(context), ColorUtils.blue(context)) {
    companion object {
        /**
         * Create a new instance of blue color.
         *
         * @param context Context for building color
         *
         * @return New instance of blue color
         *
         * @since 1.0.0
         *
         * @see [Color]
         * @see [Context]
         */
        fun create(context: Context): Color = Blue(context)
    }
}