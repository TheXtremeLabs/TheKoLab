package fr.lvmvrquxl.thekolab.colors.model.color

import android.content.Context
import fr.lvmvrquxl.thekolab.colors.utils.ColorUtils
import fr.lvmvrquxl.thekolab.colors.utils.StringUtils

internal class White private constructor(context: Context) :
    Color(StringUtils.white(context), ColorUtils.white(context)) {
    companion object {
        fun create(context: Context): Color = White(context)
    }
}