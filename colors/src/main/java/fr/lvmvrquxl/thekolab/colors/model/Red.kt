package fr.lvmvrquxl.thekolab.colors.model

import android.content.Context
import fr.lvmvrquxl.thekolab.colors.utils.ColorUtils
import fr.lvmvrquxl.thekolab.colors.utils.StringUtils

internal class Red private constructor(context: Context) :
    Color(StringUtils.red(context), ColorUtils.red(context)) {
    companion object {
        fun create(context: Context): Color = Red(context)
    }
}