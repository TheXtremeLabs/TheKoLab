package fr.lvmvrquxl.thekolab.shared.utils

import android.content.Context
import androidx.core.content.ContextCompat
import fr.lvmvrquxl.thekolab.shared.R

object SharedColorUtils {
    private val orange: Int = R.color.orange_300
    private val purple: Int = R.color.purple_300
    private val white: Int = R.color.white

    fun orange(context: Context): Int = ContextCompat.getColor(context, this.orange)

    fun purple(context: Context): Int = ContextCompat.getColor(context, this.purple)

    fun white(context: Context): Int = ContextCompat.getColor(context, this.white)
}