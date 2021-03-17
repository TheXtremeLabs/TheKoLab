package fr.lvmvrquxl.thekolab.splashscreen.view.app_name

import fr.lvmvrquxl.thekolab.shared.activity.ActivityReference
import fr.lvmvrquxl.thekolab.shared.view.LayoutView
import fr.lvmvrquxl.thekolab.splashscreen.databinding.SplashscreenAppNameBinding

/**
 * View of the layout responsible for displaying the application's name in the splashscreen's
 * activity.
 *
 * @since 2.0.0
 */
internal class AppNameLayoutView private constructor(
    private val activityReference: ActivityReference,
    private val binding: SplashscreenAppNameBinding
) : LayoutView() {
    companion object {
        /**
         * Observe the given activity's lifecycle.
         *
         * @param activityReference Reference of the splashscreen's activity
         * @param binding Binding of the view
         *
         * @since 2.0.0
         */
        fun observe(activityReference: ActivityReference, binding: SplashscreenAppNameBinding) =
            AppNameLayoutView(activityReference, binding).let { view: AppNameLayoutView ->
                activityReference.get()?.addObserver(view)
            }
    }

    override fun onDestroy() {
        this.activityReference.get()?.removeObserver(this)
    }

    override fun registerViews() {
        AppNameEndView.observe(this.activityReference, this.binding.appNameEnd)
        AppNameStartView.observe(this.activityReference, this.binding.appNameStart)
        LogoView.observe(this.activityReference, this.binding.logo)
    }
}