package fr.lvmvrquxl.thekolab.splashscreen.view

import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textview.MaterialTextView
import fr.lvmvrquxl.thekolab.shared.animation.Animation
import fr.lvmvrquxl.thekolab.shared.view.AnimatedView
import fr.lvmvrquxl.thekolab.splashscreen.viewmodel.SplashscreenState
import fr.lvmvrquxl.thekolab.splashscreen.viewmodel.SplashscreenViewModel

// TODO: Add documentation
internal abstract class AppNameView(
    private val activity: AppCompatActivity,
    private val view: MaterialTextView
) : AnimatedView() {
    companion object {
        // TODO: Add documentation
        const val TRANSLATION_X: Float = 128f
    }

    // TODO: Add documentation
    protected open val startAnimation: Runnable
        get() = this.animation
    // TODO: Add documentation
    protected val animation: Animation
        get() = Animation.animate(this.activity, this.view)
    private val exitAnimation: Runnable
        get() = this.animation
    private val updateAnimation: Runnable
        get() = this.animation

    private val viewModel: SplashscreenViewModel = SplashscreenViewModel.instance()

    override fun onCreate() = this.observeState()

    override fun showExitAnimation() = this.exitAnimation.run()

    override fun showStartAnimation() = this.startAnimation.run()

    override fun showUpdateAnimation() = this.updateAnimation.run()

    private fun observeState() =
        this.viewModel.state.observe(this.activity) { state: SplashscreenState ->
            if (SplashscreenState.SHOW_APP_NAME == state) this.showStartAnimation()
        }
}