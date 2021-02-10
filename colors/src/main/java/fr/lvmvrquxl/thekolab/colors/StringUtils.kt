package fr.lvmvrquxl.thekolab.colors

import android.content.Context
import fr.lvmvrquxl.thekolab.shared.utils.SharedStringUtils

internal object StringUtils {
    private val blue: Int = R.string.blue
    private val red: Int = R.string.red

    fun blue(context: Context): String = context.getString(this.blue)

    fun orange(context: Context): String = SharedStringUtils.orange(context)

    fun purple(context: Context): String = SharedStringUtils.purple(context)

    fun red(context: Context): String = context.getString(this.red)

    fun white(context: Context): String = SharedStringUtils.white(context)
}