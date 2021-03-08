package fr.lvmvrquxl.thekolab.splashscreen.view.version

import fr.lvmvrquxl.thekolab.shared.activity.Activity
import fr.lvmvrquxl.thekolab.shared.view.LayoutView
import fr.lvmvrquxl.thekolab.splashscreen.databinding.SplashscreenVersionBinding

/**
 * View of the layout responsible for displaying the version in the splashscreen's activity.
 *
 * @since 2.0.0
 */
internal class VersionLayoutView private constructor(
    private val activity: Activity,
    private val binding: SplashscreenVersionBinding
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
        fun observe(activity: Activity, binding: SplashscreenVersionBinding) =
            VersionLayoutView(activity, binding).let { view: VersionLayoutView ->
                activity.addObserver(view)
            }
    }

    override fun onDestroy() = this.activity.removeObserver(this)

    override fun registerViews() {
        VersionNameView.observe(this.activity, this.binding.versionName)
        VersionRootView.observe(this.activity, this.binding.root)
    }
}