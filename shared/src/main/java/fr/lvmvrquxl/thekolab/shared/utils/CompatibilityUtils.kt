package fr.lvmvrquxl.thekolab.shared.utils

import android.os.Build

/**
 * Utilities for system's compatibility.
 *
 * @since 1.0.0
 */
internal object CompatibilityUtils {
    /**
     * Check if the current device runs at least an Android Marshmallow (API >= 23).
     *
     * @return
     *  - `true` : the device runs at least an Android Marshmallow
     *  - `false` : the device runs an Android lower than Marshmallow
     *
     * @since 1.0.0
     */
    fun isMarshmallow(): Boolean = this.getSdkVersion() >= Build.VERSION_CODES.M

    private fun getSdkVersion(): Int = Build.VERSION.SDK_INT
}