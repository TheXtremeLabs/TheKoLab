package fr.lvmvrquxl.thekolab.shared.permission

import android.Manifest
import android.app.Activity

class InternetPermission(activity: Activity) :
    Permission(activity, PermissionIdentity(NAME, PERMISSION, REQUEST_CODE)) {
    companion object {
        private const val NAME: String = "internet"
        private const val PERMISSION: String = Manifest.permission.INTERNET
        private const val REQUEST_CODE: Int = 101
    }
}