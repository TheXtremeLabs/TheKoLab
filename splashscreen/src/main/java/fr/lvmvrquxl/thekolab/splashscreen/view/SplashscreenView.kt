package fr.lvmvrquxl.thekolab.splashscreen.view

import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textview.MaterialTextView
import fr.lvmvrquxl.thekolab.shared.activity.Activity
import fr.lvmvrquxl.thekolab.shared.utils.SharedStringUtils
import fr.lvmvrquxl.thekolab.shared.view.ActivityView
import fr.lvmvrquxl.thekolab.shared.view.LifecycleView
import fr.lvmvrquxl.thekolab.splashscreen.databinding.SplashscreenActivityBinding
import fr.lvmvrquxl.thekolab.splashscreen.databinding.SplashscreenAppNameBinding
import fr.lvmvrquxl.thekolab.splashscreen.databinding.SplashscreenVersionBinding
import fr.lvmvrquxl.thekolab.splashscreen.view.app_name.AppNameEndView
import fr.lvmvrquxl.thekolab.splashscreen.view.app_name.AppNameStartView
import fr.lvmvrquxl.thekolab.splashscreen.view.app_name.LogoView
import fr.lvmvrquxl.thekolab.splashscreen.view.version.VersionLayoutView

/**
 * Splashscreen's main view.
 *
 * @param activity Splashscreen's activity
 *
 * @since 1.1.0
 */
internal class SplashscreenView private constructor(private val activity: Activity) :
    ActivityView<SplashscreenActivityBinding>(activity) {
    companion object {
        /**
         * Observe the given activity's lifecycle.
         * To achieve this, a new instance of the view is created and registered as an observer.
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

    override fun onStart() {
        super.onStart()
        this.setVersionName()
    }

    override fun registerViews() {
        this.registerLogoView()
        this.registerAppNameEndView()
        this.registerAppNameStartView()
        this.registerVersionLayoutView()
    }

    private fun registerAppNameEndView() =
        this.appNameBinding?.appNameEnd?.let { appNameEnd: MaterialTextView ->
            val view: LifecycleView = AppNameEndView.create(this.activity, appNameEnd)
            this.activity.addObserver(view)
        }

    private fun registerAppNameStartView() =
        this.appNameBinding?.appNameStart?.let { appNameStart: MaterialTextView ->
            val view: LifecycleView = AppNameStartView.create(this.activity, appNameStart)
            this.activity.addObserver(view)
        }

    private fun registerLogoView() = this.appNameBinding?.logo?.let { logo: ShapeableImageView ->
        val view: LifecycleView = LogoView.create(this.activity, logo)
        this.activity.addObserver(view)
    }

    private fun registerVersionLayoutView() =
        this.versionBinding?.root?.let { versionLayout: ConstraintLayout ->
            val view: LifecycleView = VersionLayoutView.create(this.activity, versionLayout)
            this.activity.addObserver(view)
        }

    private fun setVersionName() {
        this.versionBinding?.versionName?.text = SharedStringUtils.versionName(this.activity)
    }
}