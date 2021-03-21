package fr.lvmvrquxl.thekolab.shared.permission

import fr.lvmvrquxl.thekolab.shared.activity.ActivityReference

/**
 * Permissions builder.
 *
 * @since 1.0.0
 */
class PermissionBuilder private constructor(private val activityReference: ActivityReference) {
    companion object {
        private const val ERROR_MSG: String = "No permission was provided!"

        /**
         * Create an instance of the builder.
         *
         * @param activityReference Reference of the activity that requests permission
         *
         * @since 2.0.0
         */
        fun create(activityReference: ActivityReference): PermissionBuilder =
            PermissionBuilder(activityReference)
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
     * @since 2.0.0
     */
    fun withInternet(): PermissionBuilder {
        val permission: Permission = InternetPermission.create(this.activityReference)
        this.addPermission(permission)
        return this
    }

    /**
     * Add location permission in builder.
     *
     * @return Current instance of builder
     *
     * @since 2.0.0
     */
    fun withLocation(): PermissionBuilder {
        val permission: Permission = LocationPermission.create(this.activityReference)
        this.addPermission(permission)
        return this
    }

    private fun addPermission(permission: Permission) {
        if (!this.permissions.contains(permission)) this.permissions.add(permission)
    }
}