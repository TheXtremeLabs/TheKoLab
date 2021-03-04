package fr.lvmvrquxl.thekolab.splashscreen.view

import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import fr.lvmvrquxl.thekolab.shared.view.AnimatedView
import fr.lvmvrquxl.thekolab.splashscreen.viewmodel.SplashscreenState
import fr.lvmvrquxl.thekolab.splashscreen.viewmodel.SplashscreenViewModel
import kotlinx.coroutines.Runnable

internal class VersionLayoutView private constructor(
    private val activity: AppCompatActivity,
    view: ConstraintLayout
) : AnimatedView(activity, view) {
    companion object {
        private const val DESTROY_ANIMATION: Long = 750
        private const val TRANSLATION_Y: Float = 64f

        fun create(activity: AppCompatActivity, view: ConstraintLayout): AnimatedView =
            VersionLayoutView(activity, view)
    }

    override val startAnimation: Runnable
        get() = super.animation.apply {
            this.longDuration()
            this.translationYBy(TRANSLATION_Y)
            this.onEnd { this@VersionLayoutView.destroyActivity.run() }
        }

    private val destroyActivity: Runnable
        get() = super.animation.apply {
            this.delay(DESTROY_ANIMATION)
            this.onStart { this@VersionLayoutView.viewModel.destroyActivity() }
        }

    private val viewModel: SplashscreenViewModel = SplashscreenViewModel.instance()

    init {
        super.hide()
        super.translationY(-TRANSLATION_Y)
    }

    override fun observeState() =
        this.viewModel.state.observe(this.activity) { state: SplashscreenState ->
            if (SplashscreenState.SHOW_VERSION_NAME == state) super.showStartAnimation()
        }
}