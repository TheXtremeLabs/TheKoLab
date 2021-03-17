package fr.lvmvrquxl.thekolab.colors.model.color

import android.content.Context
import fr.lvmvrquxl.thekolab.colors.utils.ColorUtils
import fr.lvmvrquxl.thekolab.colors.utils.StringUtils

/**
 * Purple color.
 *
 * @param context Context for building color
 */
internal class Purple private constructor(context: Context) :
    Color(StringUtils.purple(context), ColorUtils.purple(context)) {
    companion object {
        /**
         * Create a new instance of purple color.
         *
         * @param context Context for building color
         *
         * @return New instance of purple color
         */
        fun create(context: Context): Color = Purple(context)
    }
}