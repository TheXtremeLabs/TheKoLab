package fr.lvmvrquxl.thekolab.splashscreen.view.app_name

import com.google.android.material.textview.MaterialTextView
import fr.lvmvrquxl.thekolab.shared.activity.ActivityReference
import kotlinx.coroutines.Runnable

/**
 * View of the end of the application's name in the splashscreen's activity.
 *
 * @since 2.0.0
 */
internal class AppNameEndView private constructor(
    activityReference: ActivityReference,
    view: MaterialTextView
) : AppNameView(activityReference, view) {
    companion object {
        /**
         * Observe the given activity's lifecycle.
         *
         * @param activityReference Reference of the splashscreen's activity
         * @param view View corresponding to the end of the application's name
         *
         * @since 2.0.0
         */
        fun observe(activityReference: ActivityReference, view: MaterialTextView) =
            AppNameEndView(activityReference, view).let { v: AppNameEndView ->
                activityReference.get()?.addObserver(v)
            }
    }

    override val startAnimation: Runnable
        get() = super.animation.apply {
            this.longDuration()
            this.translationXBy(super.translationX)
            this.onEnd { super.viewModel?.showVersionName() }
        }

    override fun onResume() {
        super.onResume()
        super.translationX(-super.translationX)
    }
}