package fr.lvmvrquxl.thekolab.shared.permission

import android.Manifest
import fr.lvmvrquxl.thekolab.shared.activity.ActivityReference

/**
 * Location permission.
 *
 * @since 1.0.0
 */
internal class LocationPermission private constructor(activityReference: ActivityReference) :
    Permission(activityReference, PermissionIdentity(NAME, PERMISSION, REQUEST_CODE)) {
    companion object {
        private const val NAME: String = "location"
        private const val PERMISSION: String = Manifest.permission.ACCESS_COARSE_LOCATION
        private const val REQUEST_CODE: Int = 100

        /**
         * Create an instance of location permission.
         *
         * @param activityReference Reference of the activity that requests location permission
         *
         * @since 2.0.0
         */
        fun create(activityReference: ActivityReference): Permission =
            LocationPermission(activityReference)
    }
}