package fr.lvmvrquxl.thekolab.shared.permission

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.pm.PackageManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import fr.lvmvrquxl.thekolab.shared.activity.Activity
import fr.lvmvrquxl.thekolab.shared.activity.ActivityReference
import fr.lvmvrquxl.thekolab.shared.utils.CompatibilityUtils
import fr.lvmvrquxl.thekolab.shared.utils.DialogUtils
import fr.lvmvrquxl.thekolab.shared.utils.SharedStringUtils
import fr.lvmvrquxl.thekolab.shared.utils.ToastUtils

/**
 * Parent of all requested permissions.
 *
 * @param activityReference Reference of the activity that requests permission
 * @param identity Identity of the permission
 *
 * @since 2.0.0
 */
abstract class Permission(
    private val activityReference: ActivityReference,
    private val identity: PermissionIdentity
) {
    /**
     * Check permission's status.
     *
     * This method only check permissions on devices running an Android with at least
     * Marshmallow version (API >= 23).
     * On other devices running API <= 22, permissions are granted or not on installation of the
     * application.
     *
     * @since 2.0.0
     */
    @SuppressLint("NewApi")
    fun check() {
        if (CompatibilityUtils.isMarshmallow()) when {
            this.isGranted() -> {
            }
            true == this.shouldShowRequestPermissionRationale() -> this.showDialog()
            else -> this.requestPermission()
        }
    }

    /**
     * Check grant results.
     *
     * @param grantResults Grant results
     *
     * @since 2.0.0
     */
    fun checkGrantResults(grantResults: IntArray) {
        val text: String? = when (
            grantResults.isNotEmpty() && PackageManager.PERMISSION_GRANTED == grantResults[0]
        ) {
            true -> this.activityReference.get()?.let { activity: Activity ->
                SharedStringUtils.permissionGranted(activity)
            }
            else -> this.activityReference.get()?.let { activity: Activity ->
                SharedStringUtils.permissionDenied(activity)
            }
        }
        this.activityReference.get()?.let { activity: Activity ->
            ToastUtils.short(activity, "$text: ${this.identity.name}")
        }
    }

    /**
     * Check if permission is granted or denied.
     *
     * @return
     *  - `true` : permission is granted
     *  - `false` : permission is denied
     *
     * @since 2.0.0
     */
    @SuppressLint("NewApi")
    fun isGranted(): Boolean = PackageManager.PERMISSION_GRANTED ==
            this.activityReference.get()?.checkSelfPermission(this.identity.permission)

    @SuppressLint("NewApi")
    private fun requestPermission() {
        val permissions: Array<String> = arrayOf(this.identity.permission)
        this.activityReference.get()?.requestPermissions(permissions, this.identity.requestCode)
    }

    @SuppressLint("NewApi")
    private fun shouldShowRequestPermissionRationale(): Boolean? =
        this.activityReference.get()?.shouldShowRequestPermissionRationale(this.identity.permission)

    private fun showDialog() = this.activityReference.get()?.let { activity: Activity ->
        DialogUtils.notCancelable(
            MaterialAlertDialogBuilder(activity).apply {
                val title: String = SharedStringUtils.permissionDialogTitle(activity)
                this.setTitle(title)
                val message: String = SharedStringUtils.permissionDialogMessage(activity)
                this.setMessage("$message ${this@Permission.identity.name}.")
                val buttonText: String = SharedStringUtils.ok(activity)
                this.setPositiveButton(buttonText) { _: DialogInterface, _: Int ->
                    this@Permission.requestPermission()
                }
            }
        )
    }
}