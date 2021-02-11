package fr.lvmvrquxl.thekolab.shared.permission

import android.annotation.SuppressLint
import android.app.Activity
import android.content.DialogInterface
import android.content.pm.PackageManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import fr.lvmvrquxl.thekolab.shared.utils.CompatibilityUtils
import fr.lvmvrquxl.thekolab.shared.utils.DialogUtils
import fr.lvmvrquxl.thekolab.shared.utils.StringUtils
import fr.lvmvrquxl.thekolab.shared.utils.ToastUtils

/**
 * Parent of all requested permissions.
 *
 * @param activity Activity that requests permission
 * @param identity Identity of the permission
 *
 * @since 1.0.0
 *
 * @see [Activity]
 * @see [PermissionIdentity]
 */
abstract class Permission(
    private val activity: Activity,
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
     * @since 1.0.0
     */
    @SuppressLint("NewApi")
    fun check() {
        if (CompatibilityUtils.isMarshmallow()) when {
            this.isGranted() -> {
            }
            this.shouldShowRequestPermissionRationale() -> this.showDialog()
            else -> this.requestPermission()
        }
    }

    /**
     * Check grant results.
     *
     * @param grantResults Grant results
     *
     * @since 1.0.0
     *
     * @see [IntArray]
     */
    fun checkGrantResults(grantResults: IntArray) {
        val text: String = when (
            grantResults.isNotEmpty() && PackageManager.PERMISSION_GRANTED == grantResults[0]
        ) {
            true -> StringUtils.permissionGranted(this.activity)
            else -> StringUtils.permissionDenied(this.activity)
        }
        ToastUtils.short(this.activity, "$text: ${this.identity.name}")
    }

    /**
     * Check if permission is granted or denied.
     *
     * @return
     *  - `true` : permission is granted
     *  - `false` : permission is denied
     *
     * @since 1.0.0
     */
    @SuppressLint("NewApi")
    fun isGranted(): Boolean = PackageManager.PERMISSION_GRANTED ==
            this.activity.checkSelfPermission(this.identity.permission)

    @SuppressLint("NewApi")
    private fun requestPermission() = this.activity.requestPermissions(
        arrayOf(this.identity.permission),
        this.identity.requestCode
    )

    @SuppressLint("NewApi")
    private fun shouldShowRequestPermissionRationale(): Boolean =
        this.activity.shouldShowRequestPermissionRationale(this.identity.permission)

    private fun showDialog() = DialogUtils.notCancelable(
        MaterialAlertDialogBuilder(this.activity).apply {
            val title: String = StringUtils.permissionDialogTitle(this@Permission.activity)
            this.setTitle(title)
            val message: String = StringUtils.permissionDialogMessage(this@Permission.activity)
            this.setMessage("$message ${this@Permission.identity.name}.")
            val buttonText: String = StringUtils.ok(this@Permission.activity)
            this.setPositiveButton(buttonText) { _: DialogInterface, _: Int ->
                this@Permission.requestPermission()
            }
        }
    )
}