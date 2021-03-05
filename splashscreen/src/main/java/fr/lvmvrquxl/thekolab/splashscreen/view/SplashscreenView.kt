package fr.lvmvrquxl.thekolab.splashscreen.view

import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textview.MaterialTextView
import fr.lvmvrquxl.thekolab.shared.utils.SharedStringUtils
import fr.lvmvrquxl.thekolab.shared.view.ActivityView
import fr.lvmvrquxl.thekolab.shared.view.LifecycleObserver
import fr.lvmvrquxl.thekolab.shared.view.LifecycleView
import fr.lvmvrquxl.thekolab.splashscreen.databinding.SplashscreenActivityBinding

// TODO: Add documentation
internal class SplashscreenView private constructor(private val activity: AppCompatActivity) :
    ActivityView<SplashscreenActivityBinding>(activity) {
    companion object {
        // TODO: Add documentation
        fun create(activity: AppCompatActivity): LifecycleObserver = SplashscreenView(activity)
    }

    override fun bindView() {
        super.viewBinding = SplashscreenActivityBinding.inflate(this.activity.layoutInflater)
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
        super.viewBinding?.appNameLayout?.appNameEnd?.let { appNameEnd: MaterialTextView ->
            val view: LifecycleView = AppNameEndView.create(this.activity, appNameEnd)
            super.addView(view)
        }

    private fun registerAppNameStartView() =
        super.viewBinding?.appNameLayout?.appNameStart?.let { appNameStart: MaterialTextView ->
            val view: LifecycleView = AppNameStartView.create(this.activity, appNameStart)
            super.addView(view)
        }

    private fun registerLogoView() =
        super.viewBinding?.appNameLayout?.logo?.let { logo: ShapeableImageView ->
            val view: LifecycleView = LogoView.create(this.activity, logo)
            super.addView(view)
        }

    private fun registerVersionLayoutView() =
        super.viewBinding?.versionLayout?.root?.let { versionLayout: ConstraintLayout ->
            val view: LifecycleView = VersionLayoutView.create(this.activity, versionLayout)
            super.addView(view)
        }

    private fun setVersionName() {
        super.viewBinding?.versionLayout?.versionName?.text =
            SharedStringUtils.versionName(this.activity)
    }
}