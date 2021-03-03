package fr.lvmvrquxl.thekolab.splashscreen.view

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textview.MaterialTextView
import fr.lvmvrquxl.thekolab.shared.animation.Animation
import fr.lvmvrquxl.thekolab.shared.view.AnimatedView
import fr.lvmvrquxl.thekolab.splashscreen.viewmodel.SplashscreenState
import fr.lvmvrquxl.thekolab.splashscreen.viewmodel.SplashscreenViewModel
import kotlinx.coroutines.Runnable

// TODO: Add documentation
internal class AppNameStartView private constructor(
    private val activity: AppCompatActivity,
    private val view: MaterialTextView
) : AnimatedView() {
    companion object {
        private const val TRANSLATION_X: Float = 128f

        // TODO: Add documentation
        fun create(activity: AppCompatActivity, view: MaterialTextView): AnimatedView =
            AppNameStartView(activity, view)
    }

    private val animation: Animation
        get() = Animation.animate(this.activity, this.view)
    private val exitAnimation: Runnable
        get() = this.animation
    private val startAnimation: Runnable
        get() = Runnable {
            this.view.apply {
                this.alpha = 0f
                this.translationX = TRANSLATION_X
                this.visibility = View.VISIBLE
            }
            this.animation.apply {
                this.longDuration()
                this.translationXBy(-TRANSLATION_X)
            }.run()
        }
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