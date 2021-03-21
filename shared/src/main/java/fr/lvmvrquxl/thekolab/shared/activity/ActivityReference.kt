package fr.lvmvrquxl.thekolab.shared.activity

import java.lang.ref.WeakReference

/**
 * Weak reference of the given activity.
 *
 * @since 2.0.0
 */
class ActivityReference private constructor(activity: Activity) :
    WeakReference<Activity>(activity) {
    companion object {
        /**
         * Create a new instance of the given activity's reference.
         *
         * @param activity Activity to reference
         *
         * @since 2.0.0
         */
        fun create(activity: Activity): ActivityReference = ActivityReference(activity)
    }
}