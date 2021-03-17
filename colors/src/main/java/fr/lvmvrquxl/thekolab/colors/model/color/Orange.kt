package fr.lvmvrquxl.thekolab.colors.model.color

import android.content.Context
import fr.lvmvrquxl.thekolab.colors.utils.ColorUtils
import fr.lvmvrquxl.thekolab.colors.utils.StringUtils

/**
 * Orange color.
 *
 * @param context Context for building color
 */
internal class Orange private constructor(context: Context) :
    Color(StringUtils.orange(context), ColorUtils.orange(context)) {
    companion object {
        /**
         * Create a new instance of orange color.
         *
         * @param context Context for building color
         *
         * @return New instance of orange color
         */
        fun create(context: Context): Color = Orange(context)
    }
}