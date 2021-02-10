package fr.lvmvrquxl.thekolab.colors

import android.content.Context
import androidx.core.content.ContextCompat
import fr.lvmvrquxl.thekolab.shared.utils.SharedColorUtils

internal object ColorUtils {
    private val blue: Int = R.color.blue
    private val red: Int = R.color.red

    fun blue(context: Context): Int = ContextCompat.getColor(context, this.blue)

    fun orange(context: Context): Int = SharedColorUtils.orange(context)

    fun purple(context: Context): Int = SharedColorUtils.purple(context)

    fun red(context: Context): Int = ContextCompat.getColor(context, this.red)

    fun white(context: Context): Int = SharedColorUtils.white(context)
}