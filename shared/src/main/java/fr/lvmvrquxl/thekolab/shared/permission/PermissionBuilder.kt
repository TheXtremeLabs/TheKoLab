package fr.lvmvrquxl.thekolab.shared.permission

import android.app.Activity

/**
 * Permissions builder.
 *
 * @param activity Activity that requests permission
 *
 * @since 1.0.0
 *
 * @see [Activity]
 */
class PermissionBuilder private constructor(private val activity: Activity) {
    companion object {
        private const val ERROR_MSG: String = "No permission was provided!"

        /**
         * Create an instance of the builder.
         *
         * @param activity Activity that requests permission
         *
         * @since 1.0.0
         *
         * @see [Activity]
         */
        fun create(activity: Activity): PermissionBuilder = PermissionBuilder(activity)
    }

    private val permissions: MutableList<Permission> = mutableListOf()

    /**
     * Build permissions.
     *
     * @return List of provided permissions
     *
     * @throws [NoSuchElementException] If no permission was provided
     *
     * @since 1.0.0
     *
     * @see [List]
     * @see [Permission]
     */
    fun build(): List<Permission> = when (this.permissions.isEmpty()) {
        true -> throw NoSuchElementException(ERROR_MSG)
        else -> this.permissions
    }

    /**
     * Add internet permission in builder.
     *
     * @return Current instance of builder
     *
     * @since 1.0.0
     */
    fun withInternet(): PermissionBuilder {
        val permission: Permission = InternetPermission.create(this.activity)
        this.addPermission(permission)
        return this
    }

    /**
     * Add location permission in builder.
     *
     * @return Current instance of builder
     *
     * @since 1.0.0
     */
    fun withLocation(): PermissionBuilder {
        val permission: Permission = LocationPermission.create(this.activity)
        this.addPermission(permission)
        return this
    }

    private fun addPermission(permission: Permission) {
        if (!this.permissions.contains(permission)) this.permissions.add(permission)
    }
}