package fr.lvmvrquxl.thekolab.splashscreen.view

import fr.lvmvrquxl.thekolab.shared.activity.Activity
import fr.lvmvrquxl.thekolab.shared.activity.ActivityReference
import fr.lvmvrquxl.thekolab.shared.view.ActivityView
import fr.lvmvrquxl.thekolab.splashscreen.databinding.SplashscreenActivityBinding
import fr.lvmvrquxl.thekolab.splashscreen.databinding.SplashscreenAppNameBinding
import fr.lvmvrquxl.thekolab.splashscreen.databinding.SplashscreenVersionBinding
import fr.lvmvrquxl.thekolab.splashscreen.view.app_name.AppNameLayoutView
import fr.lvmvrquxl.thekolab.splashscreen.view.version.VersionLayoutView

/**
 * Splashscreen's main view.
 *
 * @since 2.0.0
 */
internal class SplashscreenView private constructor(
    private val activityReference: ActivityReference
) : ActivityView<SplashscreenActivityBinding>(activityReference) {
    companion object {
        /**
         * Observe the given activity's lifecycle.
         *
         * @param activityReference Reference of the splashscreen's activity
         *
         * @since 2.0.0
         */
        fun observe(activityReference: ActivityReference) {
            SplashscreenView(activityReference).let { view: SplashscreenView ->
                activityReference.get()?.addObserver(view)
            }
        }
    }

    private var appNameBinding: SplashscreenAppNameBinding? = null
    private var versionBinding: SplashscreenVersionBinding? = null

    override fun bindView() {
        super.viewBinding = this.activityReference.get()?.let { activity: Activity ->
            SplashscreenActivityBinding.inflate(activity.layoutInflater).apply {
                this@SplashscreenView.appNameBinding = this.appNameLayout
                this@SplashscreenView.versionBinding = this.versionLayout
            }
        }
    }

    override fun onDestroy() {
        this.appNameBinding = null
        this.versionBinding = null
        this.activityReference.get()?.removeObserver(this)
        super.onDestroy()
    }

    override fun registerViews() {
        this.registerAppNameContainerView()
        this.registerVersionContainerView()
    }

    private fun registerAppNameContainerView() =
        this.appNameBinding?.let { binding: SplashscreenAppNameBinding ->
            AppNameLayoutView.observe(this.activityReference, binding)
        }

    private fun registerVersionContainerView() =
        this.versionBinding?.let { binding: SplashscreenVersionBinding ->
            VersionLayoutView.observe(this.activityReference, binding)
        }
}