package fr.lvmvrquxl.thekolab.shared.utils

import android.content.Context
import fr.lvmvrquxl.thekolab.shared.R

/**
 * Utilities for accessing strings from resources.
 *
 * @since 1.0.0
 */
object SharedStringUtils {
    private val ok: Int = R.string.ok
    private val orange: Int = R.string.orange
    private val permissionDenied: Int = R.string.permission_denied
    private val permissionDialogMsg: Int = R.string.permission_dialog_msg
    private val permissionDialogTitle: Int = R.string.permission_dialog_title
    private val permissionGranted: Int = R.string.permission_granted
    private val purple: Int = R.string.purple
    private val white: Int = R.string.white

    /**
     * Get the string `OK` from resources.
     *
     * @param context Context for retrieving strings from resources
     *
     * @return The string `OK`
     *
     * @since 1.0.0
     *
     * @see Context
     */
    fun ok(context: Context): String = context.getString(this.ok)

    /**
     * Get the string `Orange` from resources.
     *
     * @param context Context for retrieving strings from resources
     *
     * @return The string `Orange`
     *
     * @since 1.0.0
     *
     * @see Context
     */
    fun orange(context: Context): String = context.getString(this.orange)

    /**
     * Get the string `Purple` from resources.
     *
     * @param context Context for retrieving strings from resources
     *
     * @return The string `Purple`
     *
     * @since 1.0.0
     *
     * @see Context
     */
    fun purple(context: Context): String = context.getString(this.purple)

    /**
     * Get the version name of the application from the package information.
     *
     * @param context Context for retrieving package manager
     *
     * @return The version name of the application
     *
     * @since 1.0.0
     *
     * @see Context
     */
    fun versionName(context: Context): String =
        context.packageManager.getPackageInfo(context.packageName, 0).versionName

    /**
     * Get the string `White` from resources.
     *
     * @param context Context for retrieving strings from resources
     *
     * @return The string `White`
     *
     * @since 1.0.0
     *
     * @see Context
     */
    fun white(context: Context): String = context.getString(this.white)

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
    internal fun permissionDenied(context: Context): String =
        context.getString(this.permissionDenied)

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
        context.getString(this.permissionDialogMsg)

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
        context.getString(this.permissionDialogTitle)

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
    internal fun permissionGranted(context: Context): String =
        context.getString(this.permissionGranted)
}