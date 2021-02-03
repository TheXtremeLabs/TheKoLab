package fr.lvmvrquxl.thekolab.shared.utils

import android.content.Context
import fr.lvmvrquxl.thekolab.shared.R

/**
 * String utilities.
 *
 * This class is responsible for accessing all the string resources of the application.
 *
 * @since 0.1.3
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
     * This method retrieves the application's name from given context (ex : an activity).
     *
     * @param context The context for retrieving the application's
     * @return The application's name
     *
     * @since 0.1.3
     */
    fun appName(context: Context): String = context.getString(APP_NAME)

    fun ok(context: Context): String = context.getString(OK)

    internal fun permissionDenied(context: Context): String = context.getString(PERMISSION_DENIED)

    internal fun permissionDialogMessage(context: Context): String =
        context.getString(PERMISSION_DIALOG_MSG)

    internal fun permissionDialogTitle(context: Context): String =
        context.getString(PERMISSION_DIALOG_TITLE)

    internal fun permissionGranted(context: Context): String = context.getString(PERMISSION_GRANTED)
}