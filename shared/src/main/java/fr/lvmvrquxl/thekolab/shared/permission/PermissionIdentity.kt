package fr.lvmvrquxl.thekolab.shared.permission

/**
 * Identity of permissions.
 *
 * @since 0.1.3
 */
data class PermissionIdentity(
    internal val name: String,
    internal val permission: String,
    internal val requestCode: Int
)
