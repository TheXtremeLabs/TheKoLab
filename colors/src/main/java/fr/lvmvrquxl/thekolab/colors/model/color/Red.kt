package fr.lvmvrquxl.thekolab.colors.model.color

import fr.lvmvrquxl.thekolab.colors.utils.ColorUtils
import fr.lvmvrquxl.thekolab.colors.utils.StringUtils
import fr.lvmvrquxl.thekolab.shared.activity.Activity
import fr.lvmvrquxl.thekolab.shared.activity.ActivityReference

/**
 * Red color.
 */
internal class Red private constructor(activityReference: ActivityReference) : Color(
    activityReference.get()?.let { activity: Activity -> StringUtils.red(activity) },
    activityReference.get()?.let { activity: Activity -> ColorUtils.red(activity) }
) {
    companion object {
        /**
         * Create a new instance of red color.
         *
         * @param activityReference Reference of the colors activity
         *
         * @return New instance of red color
         */
        fun create(activityReference: ActivityReference): Color = Red(activityReference)
    }
}