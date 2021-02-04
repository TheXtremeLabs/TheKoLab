package fr.lvmvrquxl.thekolab.shared.permission

import android.Manifest
import android.app.Activity

internal class LocationPermission(activity: Activity) :
    Permission(activity, PermissionIdentity(NAME, PERMISSION, REQUEST_CODE)) {
    companion object {
        private const val NAME: String = "location"
        private const val PERMISSION: String = Manifest.permission.ACCESS_COARSE_LOCATION
        private const val REQUEST_CODE: Int = 100
    }
}