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
    private const val FRANCE: Int = R.string.france
    private const val LIGHT_RAIN: Int = R.string.light_rain
    private const val PARIS: Int = R.string.paris
    private const val X: Int = R.string.x

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

    // TODO: Remove after testings
    fun france(context: Context): String = context.getString(FRANCE)

    // TODO: Remove after testings
    fun lightRain(context: Context): String = context.getString(LIGHT_RAIN)

    // TODO: Remove after testings
    fun paris(context: Context): String = context.getString(PARIS)

    // TODO: Remove after testings
    fun x(context: Context): String = context.getString(X)
}