package fr.lvmvrquxl.thekolab.shared.permission

import android.Manifest
import fr.lvmvrquxl.thekolab.shared.activity.ActivityReference

/**
 * Internet permission.
 *
 * @since 2.0.0
 */
internal class InternetPermission private constructor(activityReference: ActivityReference) :
    Permission(activityReference, PermissionIdentity(NAME, PERMISSION, REQUEST_CODE)) {
    companion object {
        private const val NAME: String = "internet"
        private const val PERMISSION: String = Manifest.permission.INTERNET
        private const val REQUEST_CODE: Int = 101

        /**
         * Create an instance of internet permission.
         *
         * @param activityReference Reference of the activity that needs internet permission
         *
         * @since 2.0.0
         */
        fun create(activityReference: ActivityReference): Permission =
            InternetPermission(activityReference)
    }
}