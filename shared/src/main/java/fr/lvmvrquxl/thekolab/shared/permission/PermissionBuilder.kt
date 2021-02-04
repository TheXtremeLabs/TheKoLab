package fr.lvmvrquxl.thekolab.shared.permission

import android.app.Activity

class PermissionBuilder(private val activity: Activity) {
    companion object {
        private const val ERROR_MSG: String = "No permission was provided!"
    }

    private var internet: InternetPermission? = null
    private var location: LocationPermission? = null

    fun build(): List<Permission> {
        val permissions: MutableList<Permission> = mutableListOf()
        this.internet?.let { p: InternetPermission -> permissions.add(p) }
        this.location?.let { p: LocationPermission -> permissions.add(p) }
        if (permissions.isEmpty()) throw NoSuchElementException(ERROR_MSG)
        return permissions
    }

    fun withInternet(): PermissionBuilder {
        this.internet = InternetPermission(this.activity)
        return this
    }

    fun withLocation(): PermissionBuilder {
        this.location = LocationPermission(this.activity)
        return this
    }
}