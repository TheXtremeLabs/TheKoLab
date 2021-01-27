package fr.lvmvrquxl.thekolab.utils

import android.content.Context
import fr.lvmvrquxl.thekolab.R

/**
 * String utilities.
 *
 * This class is responsible for accessing all the string resources of the application.
 *
 * @since 0.1.3
 */
object Strings {
    private const val APP_NAME: Int = R.string.app_name

    /**
     * Get the application's name.
     *
     * This method retrieves the application's name from given context (ex : an activity).
     *
     * @param context The context for retrieving the application's
     * @return The application's name
     *
     * @since 0.1.3
     */
    fun appName(context: Context): String = context.getString(APP_NAME)
}