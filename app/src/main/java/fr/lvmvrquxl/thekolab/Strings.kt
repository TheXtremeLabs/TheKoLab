package fr.lvmvrquxl.thekolab

import android.content.Context

object Strings {
    private const val APP_NAME: Int = R.string.app_name

    fun appName(context: Context): String = context.getString(this.APP_NAME)
}