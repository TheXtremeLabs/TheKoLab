package fr.lvmvrquxl.thekolab.splashscreen.view.version

import com.google.android.material.textview.MaterialTextView
import fr.lvmvrquxl.thekolab.shared.activity.Activity
import fr.lvmvrquxl.thekolab.shared.utils.SharedStringUtils
import fr.lvmvrquxl.thekolab.shared.view.LifecycleView

/**
 * View of the version name in the splashscreen's activity.
 *
 * @since 2.0.0
 */
internal class VersionNameView private constructor(
    private val activity: Activity,
    private val view: MaterialTextView
) : LifecycleView() {
    companion object {
        /**
         * Observe the given activity's lifecycle.
         *
         * @param activity Splashscreen's activity
         * @param view View corresponding to the version name
         *
         * @since 2.0.0
         */
        fun observe(activity: Activity, view: MaterialTextView) =
            VersionNameView(activity, view).let { v: VersionNameView -> activity.addObserver(v) }
    }

    override fun onCreate() = this.setText()

    private fun setText() {
        this.view.text = SharedStringUtils.versionName(this.activity)
    }
}