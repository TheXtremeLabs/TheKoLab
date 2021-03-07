package fr.lvmvrquxl.thekolab.splashscreen.view.app_name

import fr.lvmvrquxl.thekolab.shared.activity.Activity
import fr.lvmvrquxl.thekolab.shared.view.ContainerView
import fr.lvmvrquxl.thekolab.splashscreen.databinding.SplashscreenAppNameBinding

/**
 * View of the layout responsible for displaying the application's name in the splashscreen's
 * activity.
 *
 * @since 2.0.0
 */
internal class AppNameContainerView private constructor(
    private val activity: Activity,
    private val binding: SplashscreenAppNameBinding
) : ContainerView() {
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
            AppNameContainerView(activity, binding).let { view: AppNameContainerView ->
                activity.addObserver(view)
            }
    }

    override fun onDestroy() {
        this.activity.removeObserver(this)
        super.onDestroy()
    }

    override fun registerViews() {
        AppNameEndView.observe(this.activity, this.binding.appNameEnd)
        AppNameStartView.observe(this.activity, this.binding.appNameStart)
        LogoView.observe(this.activity, this.binding.logo)
    }
}