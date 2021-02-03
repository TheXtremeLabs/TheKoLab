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
internal object StringUtils {
    private const val APP_NAME: Int = R.string.app_name
    private const val PERMISSION_DENIED: Int = R.string.permission_denied
    private const val PERMISSION_DIALOG_MSG: Int = R.string.permission_dialog_msg
    private const val PERMISSION_DIALOG_TITLE: Int = R.string.permission_dialog_title
    private const val PERMISSION_GRANTED: Int = R.string.permission_granted
    private const val OK: Int = R.string.ok

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
    internal fun appName(context: Context): String = context.getString(APP_NAME)

    internal fun ok(context: Context): String = context.getString(OK)

    internal fun permissionDenied(context: Context): String = context.getString(PERMISSION_DENIED)

    internal fun permissionDialogMessage(context: Context): String =
        context.getString(PERMISSION_DIALOG_MSG)

    internal fun permissionDialogTitle(context: Context): String =
        context.getString(PERMISSION_DIALOG_TITLE)

    internal fun permissionGranted(context: Context): String = context.getString(PERMISSION_GRANTED)
}