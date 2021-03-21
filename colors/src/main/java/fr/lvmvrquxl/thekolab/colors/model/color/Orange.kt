package fr.lvmvrquxl.thekolab.colors.model.color

import fr.lvmvrquxl.thekolab.colors.utils.ColorUtils
import fr.lvmvrquxl.thekolab.colors.utils.StringUtils
import fr.lvmvrquxl.thekolab.shared.activity.Activity
import fr.lvmvrquxl.thekolab.shared.activity.ActivityReference

/**
 * Orange color.
 */
internal class Orange private constructor(activityReference: ActivityReference) : Color(
    activityReference.get()?.let { activity: Activity -> StringUtils.orange(activity) },
    activityReference.get()?.let { activity: Activity -> ColorUtils.orange(activity) }
) {
    companion object {
        /**
         * Create a new instance of orange color.
         *
         * @param activityReference Reference of the colors activity
         *
         * @return New instance of orange color
         */
        fun create(activityReference: ActivityReference): Color = Orange(activityReference)
    }
}