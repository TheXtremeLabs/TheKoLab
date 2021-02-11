package fr.lvmvrquxl.thekolab.shared.utils

import android.content.Context
import fr.lvmvrquxl.thekolab.shared.R

/**
 * Utilities for accessing strings from resources.
 *
 * @since 1.0.0
 */
object StringUtils {
    private val APP_NAME: Int = R.string.app_name
    private val PERMISSION_DENIED: Int = R.string.permission_denied
    private val PERMISSION_DIALOG_MSG: Int = R.string.permission_dialog_msg
    private val PERMISSION_DIALOG_TITLE: Int = R.string.permission_dialog_title
    private val PERMISSION_GRANTED: Int = R.string.permission_granted
    private val OK: Int = R.string.ok

    /**
     * Get the application's name.
     *
     * @param context Context for retrieving strings from resources
     *
     * @return The application's name
     *
     * @since 1.0.0
     *
     * @see [Context]
     */
    fun appName(context: Context): String = context.getString(APP_NAME)

    /**
     * Get the string `OK` from resources.
     *
     * @param context Context for retrieving strings from resources
     *
     * @return The string `OK`
     *
     * @since 1.0.0
     *
     * @see [Context]
     */
    fun ok(context: Context): String = context.getString(OK)

    /**
     * Get the string corresponding to permission denied from resources.
     *
     * @param context Context for retrieving strings from resources
     *
     * @return The string `OK`
     *
     * @since 1.0.0
     *
     * @see [Context]
     */
    internal fun permissionDenied(context: Context): String = context.getString(PERMISSION_DENIED)

    /**
     * Get the permission dialog's message from resources.
     *
     * @param context Context for retrieving strings from resources
     *
     * @return The permission dialog's message
     *
     * @since 1.0.0
     *
     * @see [Context]
     */
    internal fun permissionDialogMessage(context: Context): String =
        context.getString(PERMISSION_DIALOG_MSG)

    /**
     * Get the permission dialog's title from resources.
     *
     * @param context Context for retrieving strings from resources
     *
     * @return The permission dialog's title
     *
     * @since 1.0.0
     *
     * @see [Context]
     */
    internal fun permissionDialogTitle(context: Context): String =
        context.getString(PERMISSION_DIALOG_TITLE)

    /**
     * Get the string corresponding to permission granted from resources.
     *
     * @param context Context for retrieving strings from resources
     *
     * @return The string corresponding to permission granted
     *
     * @since 1.0.0
     *
     * @see [Context]
     */
    internal fun permissionGranted(context: Context): String = context.getString(PERMISSION_GRANTED)
}