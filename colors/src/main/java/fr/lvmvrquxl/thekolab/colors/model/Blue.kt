package fr.lvmvrquxl.thekolab.colors.model

import android.content.Context
import fr.lvmvrquxl.thekolab.colors.utils.ColorUtils
import fr.lvmvrquxl.thekolab.colors.utils.StringUtils

internal class Blue private constructor(context: Context) :
    Color(StringUtils.blue(context), ColorUtils.blue(context)) {
    companion object {
        fun create(context: Context): Color = Blue(context)
    }
}