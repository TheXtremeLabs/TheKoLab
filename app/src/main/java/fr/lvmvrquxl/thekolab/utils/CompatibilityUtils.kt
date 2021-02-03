package fr.lvmvrquxl.thekolab.utils

import android.os.Build

object CompatibilityUtils {
    /**
     * Check if the current device runs at least an Android Marshmallow.
     *
     * This method checks if the application is running on a device with an API level higher
     * than 23.
     *
     * @return
     * - `true` if the device runs at least an Android Marshmallow
     * - `false` if the device runs an Android lower than Marshmallow
     */
    fun isMarshmallow(): Boolean = this.getSdkVersion() >= Build.VERSION_CODES.M

    private fun getSdkVersion(): Int = Build.VERSION.SDK_INT
}