package fr.lvmvrquxl.thekolab.base.permission

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import fr.lvmvrquxl.thekolab.utils.ApiCompatibility
import java.util.*

class LocationPermission(private val activity: Activity) {
    companion object {
        private const val LOCATION_PERMISSION: String = Manifest.permission.ACCESS_COARSE_LOCATION
        private const val LOCATION_REQUEST_CODE: Int = 100
    }

    @SuppressLint("NewApi")
    fun check() {
        if (ApiCompatibility.isMarshmallow()) when {
            this.isGranted() -> {
            }
            this.shouldShowRequestPermissionRationale() -> this.showDialog()
            else -> this.requestPermissions()
        }
    }

    fun checkGrantResults(grantResults: IntArray) =
        when (grantResults.isNotEmpty() && PackageManager.PERMISSION_GRANTED == grantResults[0]) {
            true -> this.showToast("Location permission granted")
            else -> this.showToast("Location permission denied")
        }

    @SuppressLint("NewApi")
    internal fun isGranted(): Boolean =
        PackageManager.PERMISSION_GRANTED == this.activity.checkSelfPermission(LOCATION_PERMISSION)

    @SuppressLint("NewApi")
    private fun requestPermissions() =
        this.activity.requestPermissions(arrayOf(LOCATION_PERMISSION), LOCATION_REQUEST_CODE)

    @SuppressLint("NewApi")
    private fun shouldShowRequestPermissionRationale(): Boolean =
        this.activity.shouldShowRequestPermissionRationale(LOCATION_PERMISSION)

    private fun showDialog() = AlertDialog.Builder(this.activity)
        .apply {
            this.setTitle("Permission required")
            this.setMessage("Permission to access your location is required for this feature")
            this.setPositiveButton("Ok") { _: DialogInterface, _: Int ->
                this@LocationPermission.requestPermissions()
            }
            this.setCancelable(false)
        }
        .create()
        .show()

    private fun showToast(text: String) =
        Toast.makeText(this.activity, text, Toast.LENGTH_SHORT).show()
}