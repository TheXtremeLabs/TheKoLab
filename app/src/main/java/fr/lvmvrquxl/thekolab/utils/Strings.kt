package fr.lvmvrquxl.thekolab.utils

import android.content.Context
import fr.lvmvrquxl.thekolab.R

object Strings {
    private const val APP_NAME: Int = R.string.app_name

    fun appName(context: Context): String = context.getString(APP_NAME)
}