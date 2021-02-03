package fr.lvmvrquxl.thekolab.shared.permission

data class PermissionIdentity(
    internal val name: String,
    internal val permission: String,
    internal val requestCode: Int
)
