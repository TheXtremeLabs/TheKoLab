package fr.lvmvrquxl.thekolab.colors.model.color

import fr.lvmvrquxl.thekolab.colors.utils.ColorUtils
import fr.lvmvrquxl.thekolab.colors.utils.StringUtils
import fr.lvmvrquxl.thekolab.shared.activity.Activity
import fr.lvmvrquxl.thekolab.shared.activity.ActivityReference

/**
 * White color.
 */
internal class White private constructor(activityReference: ActivityReference) : Color(
    activityReference.get()?.let { activity: Activity -> StringUtils.white(activity) },
    activityReference.get()?.let { activity: Activity -> ColorUtils.white(activity) }
) {
    companion object {
        /**
         * Create a new instance of white color.
         *
         * @param activityReference Reference of the colors activity
         *
         * @return New instance of white color
         */
        fun create(activityReference: ActivityReference): Color = White(activityReference)
    }
}