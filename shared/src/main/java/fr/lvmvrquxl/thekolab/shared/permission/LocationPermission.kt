package fr.lvmvrquxl.thekolab.shared.permission

import android.Manifest
import android.app.Activity

/**
 * Location permission.
 *
 * @param activity Activity that requests permission
 *
 * @since 1.0.0
 *
 * @see [Activity]
 * @see [Permission]
 * @see [PermissionIdentity]
 */
internal class LocationPermission private constructor(activity: Activity) :
    Permission(activity, PermissionIdentity(NAME, PERMISSION, REQUEST_CODE)) {
    companion object {
        private const val NAME: String = "location"
        private const val PERMISSION: String = Manifest.permission.ACCESS_COARSE_LOCATION
        private const val REQUEST_CODE: Int = 100

        /**
         * Create an instance of location permission.
         *
         * @param activity Activity that requests permission
         *
         * @since 1.0.0
         *
         * @see [Activity]
         * @see [Permission]
         */
        fun create(activity: Activity): Permission = LocationPermission(activity)
    }
}