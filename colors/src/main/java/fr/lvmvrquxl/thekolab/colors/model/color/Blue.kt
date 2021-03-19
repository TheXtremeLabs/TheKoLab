package fr.lvmvrquxl.thekolab.colors.model.color

import fr.lvmvrquxl.thekolab.colors.utils.ColorUtils
import fr.lvmvrquxl.thekolab.colors.utils.StringUtils
import fr.lvmvrquxl.thekolab.shared.activity.Activity
import fr.lvmvrquxl.thekolab.shared.activity.ActivityReference

/**
 * Blue color.
 */
internal class Blue private constructor(activityReference: ActivityReference) : Color(
    activityReference.get()?.let { activity: Activity -> StringUtils.blue(activity) },
    activityReference.get()?.let { activity: Activity -> ColorUtils.blue(activity) }
) {
    companion object {
        /**
         * Create a new instance of blue color.
         *
         * @param activityReference Reference of the colors activity
         *
         * @return New instance of blue color
         */
        fun create(activityReference: ActivityReference): Color = Blue(activityReference)
    }
}