package fr.lvmvrquxl.thekolab.base.permission

import android.annotation.SuppressLint
import android.app.Activity
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import fr.lvmvrquxl.thekolab.utils.ApiCompatibility

internal abstract class Permission(
    private val activity: Activity,
    private val identity: PermissionIdentity
) {
    @SuppressLint("NewApi")
    internal fun check() {
        if (ApiCompatibility.isMarshmallow()) when {
            this.isGranted() -> {
            }
            this.shouldShowRequestPermissionRationale() -> this.showDialog()
            else -> this.requestPermission()
        }
    }

    internal fun checkGrantResults(grantResults: IntArray) =
        when (grantResults.isNotEmpty() && PackageManager.PERMISSION_GRANTED == grantResults[0]) {
            true -> this.showToast("Permission granted: ${this.identity.name}")
            else -> this.showToast("Permission denied: ${this.identity.name}")
        }

    @SuppressLint("NewApi")
    internal fun isGranted(): Boolean = PackageManager.PERMISSION_GRANTED ==
            this.activity.checkSelfPermission(this.identity.permission)

    @SuppressLint("NewApi")
    private fun requestPermission() = this.activity.requestPermissions(
        arrayOf(this.identity.permission),
        this.identity.requestCode
    )

    @SuppressLint("NewApi")
    private fun shouldShowRequestPermissionRationale(): Boolean =
        this.activity.shouldShowRequestPermissionRationale(this.identity.permission)

    private fun showDialog() = AlertDialog.Builder(this.activity)
        .apply {
            this.setTitle("Permission required")
            this.setMessage(
                "Permission to access your ${this@Permission.identity.name} is required for this feature"
            )
            this.setPositiveButton("Ok") { _: DialogInterface, _: Int ->
                this@Permission.requestPermission()
            }
            this.setCancelable(false)
        }
        .create()
        .show()

    private fun showToast(text: String) =
        Toast.makeText(this.activity, text, Toast.LENGTH_SHORT).show()
}