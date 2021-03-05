package fr.lvmvrquxl.thekolab.splashscreen.view.app_name

import com.google.android.material.textview.MaterialTextView
import fr.lvmvrquxl.thekolab.shared.activity.Activity
import kotlinx.coroutines.Runnable

/**
 * View of the start of the application's name in the splashscreen's activity.
 *
 * @since 1.1.0
 */
internal class AppNameStartView private constructor(activity: Activity, view: MaterialTextView) :
    AppNameView(activity, view) {
    companion object {
        /**
         * Observe the given activity's lifecycle.
         *
         * @param activity Splashscreen's activity
         * @param view View corresponding to the start of the application's name
         *
         * @since 1.1.0
         */
        fun observe(activity: Activity, view: MaterialTextView) =
            AppNameStartView(activity, view).let { v: AppNameStartView -> activity.addObserver(v) }
    }

    override val startAnimation: Runnable
        get() = super.animation.apply {
            this.longDuration()
            this.translationXBy(-super.translationX)
        }

    override fun onResume() {
        super.onResume()
        super.translationX(super.translationX)
    }
}