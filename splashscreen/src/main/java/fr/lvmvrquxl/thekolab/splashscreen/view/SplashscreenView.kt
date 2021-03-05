package fr.lvmvrquxl.thekolab.splashscreen.view

import fr.lvmvrquxl.thekolab.shared.activity.Activity
import fr.lvmvrquxl.thekolab.shared.view.ActivityView
import fr.lvmvrquxl.thekolab.splashscreen.databinding.SplashscreenActivityBinding
import fr.lvmvrquxl.thekolab.splashscreen.databinding.SplashscreenAppNameBinding
import fr.lvmvrquxl.thekolab.splashscreen.databinding.SplashscreenVersionBinding
import fr.lvmvrquxl.thekolab.splashscreen.view.app_name.AppNameContainerView
import fr.lvmvrquxl.thekolab.splashscreen.view.version.VersionContainerView

/**
 * Splashscreen's main view.
 *
 * @since 1.1.0
 */
internal class SplashscreenView private constructor(private val activity: Activity) :
    ActivityView<SplashscreenActivityBinding>(activity) {
    companion object {
        /**
         * Observe the given activity's lifecycle.
         *
         * @param activity Splashscreen's activity
         *
         * @since 1.1.0
         */
        fun observe(activity: Activity) =
            SplashscreenView(activity).let { view: SplashscreenView -> activity.addObserver(view) }
    }

    private var appNameBinding: SplashscreenAppNameBinding? = null
    private var versionBinding: SplashscreenVersionBinding? = null

    override fun bindView() {
        super.viewBinding =
            SplashscreenActivityBinding.inflate(this.activity.layoutInflater).apply {
                this@SplashscreenView.appNameBinding = this.appNameLayout
                this@SplashscreenView.versionBinding = this.versionLayout
            }
    }

    override fun onDestroy() {
        this.appNameBinding = null
        this.versionBinding = null
        this.activity.removeObserver(this)
        super.onDestroy()
    }

    override fun registerViews() {
        this.registerAppNameContainerView()
        this.registerVersionContainerView()
    }

    private fun registerAppNameContainerView() =
        super.viewBinding?.appNameLayout?.let { binding: SplashscreenAppNameBinding ->
            AppNameContainerView.observe(this.activity, binding)
        }

    private fun registerVersionContainerView() =
        super.viewBinding?.versionLayout?.let { binding: SplashscreenVersionBinding ->
            VersionContainerView.observe(this.activity, binding)
        }
}