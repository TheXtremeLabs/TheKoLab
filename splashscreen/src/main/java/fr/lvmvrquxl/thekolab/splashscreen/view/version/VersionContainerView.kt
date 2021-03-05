package fr.lvmvrquxl.thekolab.splashscreen.view.version

import fr.lvmvrquxl.thekolab.shared.activity.Activity
import fr.lvmvrquxl.thekolab.shared.view.ContainerView
import fr.lvmvrquxl.thekolab.splashscreen.databinding.SplashscreenVersionBinding

/**
 * View of the layout responsible for displaying the version in the splashscreen's activity.
 *
 * @since 1.1.0
 */
internal class VersionContainerView private constructor(
    private val activity: Activity,
    private val binding: SplashscreenVersionBinding
) : ContainerView() {
    companion object {
        /**
         * Observe the given activity's lifecycle.
         *
         * @param activity Splashscreen's activity
         * @param binding Binding of the view
         *
         * @since 1.1.0
         */
        fun observe(activity: Activity, binding: SplashscreenVersionBinding) =
            VersionContainerView(activity, binding).let { view: VersionContainerView ->
                activity.addObserver(view)
            }
    }

    override fun onDestroy() {
        this.activity.removeObserver(this)
        super.onDestroy()
    }

    override fun registerViews() {
        VersionNameView.observe(this.activity, this.binding.versionName)
        VersionRootView.observe(this.activity, this.binding.root)
    }
}