package fr.lvmvrquxl.thekolab.colors.model.color

import android.content.Context
import fr.lvmvrquxl.thekolab.colors.utils.ColorUtils
import fr.lvmvrquxl.thekolab.colors.utils.StringUtils

/**
 * White color.
 *
 * @param context Context for building color
 */
internal class White private constructor(context: Context) :
    Color(StringUtils.white(context), ColorUtils.white(context)) {
    companion object {
        /**
         * Create a new instance of white color.
         *
         * @param context Context for building color
         *
         * @return New instance of white color
         */
        fun create(context: Context): Color = White(context)
    }
}