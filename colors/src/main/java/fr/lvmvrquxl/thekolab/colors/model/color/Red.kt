package fr.lvmvrquxl.thekolab.colors.model.color

import android.content.Context
import fr.lvmvrquxl.thekolab.colors.utils.ColorUtils
import fr.lvmvrquxl.thekolab.colors.utils.StringUtils

/**
 * Red color.
 *
 * @param context Context for building color
 */
internal class Red private constructor(context: Context) :
    Color(StringUtils.red(context), ColorUtils.red(context)) {
    companion object {
        /**
         * Create a new instance of red color.
         *
         * @param context Context for building color
         *
         * @return New instance of red color
         */
        fun create(context: Context): Color = Red(context)
    }
}