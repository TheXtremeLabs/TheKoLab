package fr.lvmvrquxl.thekolab.colors.model.color

import android.content.Context
import fr.lvmvrquxl.thekolab.colors.utils.ColorUtils
import fr.lvmvrquxl.thekolab.colors.utils.StringUtils

internal class Orange private constructor(context: Context) :
    Color(StringUtils.orange(context), ColorUtils.orange(context)) {
    companion object {
        fun create(context: Context): Color = Orange(context)
    }
}