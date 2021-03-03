package fr.lvmvrquxl.thekolab.splashscreen.view

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import fr.lvmvrquxl.thekolab.shared.animation.Animation
import fr.lvmvrquxl.thekolab.shared.view.AnimatedView

// TODO: Add documentation
internal abstract class SplashscreenAnimatedView(
    private val activity: AppCompatActivity,
    private val view: View
) : AnimatedView() {
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

    protected abstract fun observeState()

    override fun onCreate() = this.observeState()

    override fun showExitAnimation() = this.exitAnimation.run()

    override fun showStartAnimation() = this.startAnimation.run()

    override fun showUpdateAnimation() = this.updateAnimation.run()
}