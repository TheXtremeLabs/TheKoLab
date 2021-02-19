package fr.lvmvrquxl.thekolab.colors.model.color

import android.content.Context
import fr.lvmvrquxl.thekolab.colors.utils.ColorUtils
import fr.lvmvrquxl.thekolab.colors.utils.StringUtils

internal class Purple private constructor(context: Context) :
    Color(StringUtils.purple(context), ColorUtils.purple(context)) {
    companion object {
        fun create(context: Context): Color = Purple(context)
    }
}