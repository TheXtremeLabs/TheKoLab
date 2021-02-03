package fr.lvmvrquxl.thekolab.base.permission

internal data class PermissionIdentity(
    internal val name: String,
    internal val permission: String,
    internal val requestCode: Int
)
