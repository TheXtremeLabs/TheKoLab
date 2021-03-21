package fr.lvmvrquxl.thekolab.colors.model.color

import fr.lvmvrquxl.thekolab.colors.utils.ColorUtils
import fr.lvmvrquxl.thekolab.colors.utils.StringUtils
import fr.lvmvrquxl.thekolab.shared.activity.Activity
import fr.lvmvrquxl.thekolab.shared.activity.ActivityReference

/**
 * Purple color.
 */
internal class Purple private constructor(activityReference: ActivityReference) : Color(
    activityReference.get()?.let { activity: Activity -> StringUtils.purple(activity) },
    activityReference.get()?.let { activity: Activity -> ColorUtils.purple(activity) }
) {
    companion object {
        /**
         * Create a new instance of purple color.
         *
         * @param activityReference Reference of the colors activity
         *
         * @return New instance of purple color
         */
        fun create(activityReference: ActivityReference): Color = Purple(activityReference)
    }
}