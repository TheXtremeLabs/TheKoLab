package fr.lvmvrquxl.thekolab.splashscreen.view.app_name

import fr.lvmvrquxl.thekolab.shared.activity.Activity
import fr.lvmvrquxl.thekolab.shared.view.LayoutView
import fr.lvmvrquxl.thekolab.splashscreen.databinding.SplashscreenAppNameBinding

/**
 * View of the layout responsible for displaying the application's name in the splashscreen's
 * activity.
 *
 * @since 2.0.0
 */
internal class AppNameLayoutView private constructor(
    private val activity: Activity,
    private val binding: SplashscreenAppNameBinding
) : LayoutView() {
    companion object {
        /**
         * Observe the given activity's lifecycle.
         *
         * @param activity Splashscreen's activity
         * @param binding Binding of the view
         *
         * @since 2.0.0
         */
        fun observe(activity: Activity, binding: SplashscreenAppNameBinding) =
            AppNameLayoutView(activity, binding).let { view: AppNameLayoutView ->
                activity.addObserver(view)
            }
    }

    override fun onDestroy() = this.activity.removeObserver(this)

    override fun registerViews() {
        AppNameEndView.observe(this.activity, this.binding.appNameEnd)
        AppNameStartView.observe(this.activity, this.binding.appNameStart)
        LogoView.observe(this.activity, this.binding.logo)
    }
}