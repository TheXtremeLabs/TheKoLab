package fr.lvmvrquxl.thekolab.splashscreen.view.version

import com.google.android.material.textview.MaterialTextView
import fr.lvmvrquxl.thekolab.shared.activity.Activity
import fr.lvmvrquxl.thekolab.shared.activity.ActivityReference
import fr.lvmvrquxl.thekolab.shared.utils.SharedStringUtils
import fr.lvmvrquxl.thekolab.shared.view.LifecycleView

/**
 * View of the version name in the splashscreen's activity.
 *
 * @since 2.0.0
 */
internal class VersionNameView private constructor(
    private val activityReference: ActivityReference,
    private val view: MaterialTextView
) : LifecycleView() {
    companion object {
        /**
         * Observe the given activity's lifecycle.
         *
         * @param activityReference Reference of the splashscreen's activity
         * @param view View corresponding to the version name
         *
         * @since 2.0.0
         */
        fun observe(activityReference: ActivityReference, view: MaterialTextView) =
            VersionNameView(activityReference, view).let { v: VersionNameView ->
                activityReference.get()?.addObserver(v)
            }
    }

    override fun onCreate() = this.setText()

    private fun setText() {
        this.activityReference.get()?.let { activity: Activity ->
            this.view.text = SharedStringUtils.versionName(activity)
        }
    }
}