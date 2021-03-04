package fr.lvmvrquxl.thekolab.splashscreen.view

import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textview.MaterialTextView
import fr.lvmvrquxl.thekolab.shared.utils.SharedStringUtils
import fr.lvmvrquxl.thekolab.shared.view.ActivityView
import fr.lvmvrquxl.thekolab.shared.view.LifecycleView
import fr.lvmvrquxl.thekolab.splashscreen.databinding.SplashscreenActivityBinding
import fr.lvmvrquxl.thekolab.splashscreen.viewmodel.SplashscreenState
import fr.lvmvrquxl.thekolab.splashscreen.viewmodel.SplashscreenViewModel

// TODO: Add documentation
internal class SplashscreenView private constructor(private val activity: AppCompatActivity) :
    ActivityView<SplashscreenActivityBinding>() {
    companion object {
        // TODO: Add documentation
        fun create(activity: AppCompatActivity): ActivityView<SplashscreenActivityBinding> =
            SplashscreenView(activity)
    }

    private val stateObserver: Observer<SplashscreenState>
        get() = Observer { state: SplashscreenState ->
            when (state) {
                SplashscreenState.DESTROY -> this.onDestroy()
                SplashscreenState.PAUSE -> super.onPause()
                SplashscreenState.RESUME -> super.onResume()
                SplashscreenState.START -> super.onStart()
                SplashscreenState.STOP -> super.onStop()
                else -> {
                }
            }
        }

    private var viewModel: SplashscreenViewModel? = null

    init {
        this.observeViewModel()
    }

    override fun bindView() {
        super.viewBinding = SplashscreenActivityBinding.inflate(this.activity.layoutInflater)
    }

    override fun onCreate() {
        super.onCreate()
        this.setVersionName()
    }

    override fun onDestroy() {
        this.destroyViewModel()
        super.onDestroy()
    }

    override fun registerViews() {
        this.registerLogoView()
        this.registerAppNameEndView()
        this.registerAppNameStartView()
        this.registerVersionLayoutView()
    }

    private fun destroyViewModel() {
        this.viewModel = null
    }

    private fun observeViewModel() {
        this.viewModel = SplashscreenViewModel.instance().apply {
            this.state.observe(this@SplashscreenView.activity, this@SplashscreenView.stateObserver)
        }
    }

    private fun registerAppNameEndView() =
        super.viewBinding?.appNameEnd?.let { appNameEnd: MaterialTextView ->
            val view: LifecycleView = AppNameEndView.create(this.activity, appNameEnd)
            super.addView(view)
        }

    private fun registerAppNameStartView() =
        super.viewBinding?.appNameStart?.let { appNameStart: MaterialTextView ->
            val view: LifecycleView = AppNameStartView.create(this.activity, appNameStart)
            super.addView(view)
        }

    private fun registerLogoView() = super.viewBinding?.logo?.let { logo: ShapeableImageView ->
        val view: LifecycleView = LogoView.create(this.activity, logo)
        super.addView(view)
    }

    private fun registerVersionLayoutView() =
        super.viewBinding?.versionLayout?.let { versionLayout: ConstraintLayout ->
            val view: LifecycleView = VersionLayoutView.create(this.activity, versionLayout)
            super.addView(view)
        }

    private fun setVersionName() {
        super.viewBinding?.versionName?.text = SharedStringUtils.versionName(this.activity)
    }
}