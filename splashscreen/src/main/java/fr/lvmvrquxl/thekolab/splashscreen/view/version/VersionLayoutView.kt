package fr.lvmvrquxl.thekolab.splashscreen.view.version

import fr.lvmvrquxl.thekolab.shared.activity.ActivityReference
import fr.lvmvrquxl.thekolab.shared.view.LayoutView
import fr.lvmvrquxl.thekolab.splashscreen.databinding.SplashscreenVersionBinding

/**
 * View of the layout responsible for displaying the version in the splashscreen's activity.
 *
 * @since 2.0.0
 */
internal class VersionLayoutView private constructor(
    private val activityReference: ActivityReference,
    private val binding: SplashscreenVersionBinding
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
        fun observe(activityReference: ActivityReference, binding: SplashscreenVersionBinding) =
            VersionLayoutView(activityReference, binding).let { view: VersionLayoutView ->
                activityReference.get()?.addObserver(view)
            }
    }

    override fun onDestroy() {
        this.activityReference.get()?.removeObserver(this)
    }

    override fun registerViews() {
        VersionNameView.observe(this.activityReference, this.binding.versionName)
        VersionRootView.observe(this.activityReference, this.binding.root)
    }
}