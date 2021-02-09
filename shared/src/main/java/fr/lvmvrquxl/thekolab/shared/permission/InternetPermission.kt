package fr.lvmvrquxl.thekolab.shared.permission

import android.Manifest
import android.app.Activity

/**
 * Internet permission.
 *
 * @param activity Activity that requests permission
 *
 * @since 1.0.0
 *
 * @see [Activity]
 * @see [Permission]
 * @see [PermissionIdentity]
 */
internal class InternetPermission private constructor(activity: Activity) :
    Permission(activity, PermissionIdentity(NAME, PERMISSION, REQUEST_CODE)) {
    companion object {
        private const val NAME: String = "internet"
        private const val PERMISSION: String = Manifest.permission.INTERNET
        private const val REQUEST_CODE: Int = 101

        /**
         * Create an instance of internet permission.
         *
         * @param activity Activity that needs the permission
         *
         * @since 1.0.0
         *
         * @see [Activity]
         * @see [Permission]
         */
        fun create(activity: Activity): Permission = InternetPermission(activity)
    }
}