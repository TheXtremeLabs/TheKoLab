package fr.lvmvrquxl.thekolab.shared.view

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import fr.lvmvrquxl.thekolab.shared.animation.Animation
import kotlinx.coroutines.Runnable

/**
 * Parent of all animated views.
 *
 * @since 1.0.0
 *
 * @see LifecycleView
 */
abstract class AnimatedView(
    private val activity: AppCompatActivity,
    private val view: View
) : LifecycleView() {
    override fun onCreate() = this.observeState()

    // TODO: Add documentation
    protected abstract fun observeState()

    /**
     * Animation executed when the user closes the activity.
     *
     * @since 1.0.0
     *
     * @see Runnable
     */
    protected open val exitAnimation: Runnable
        get() = this.animation

    /**
     * Animation executed when the activity starts.
     *
     * @since 1.0.0
     *
     * @see Runnable
     */
    protected open val startAnimation: Runnable
        get() = this.animation

    /**
     * Animation executed when displayed data is updated.
     *
     * @since 1.0.0
     *
     * @see Runnable
     */
    protected open val updateAnimation: Runnable
        get() = this.animation

    /**
     * Animation instance for the view.
     *
     * @since 1.0.0
     *
     * @see Animation
     */
    protected val animation: Animation
        get() = Animation.animate(this.activity, this.view)

    /**
     * Hide the current view.
     *
     * @since 1.0.0
     */
    protected fun hide() {
        this.view.alpha = 0f
    }

    // TODO: Add documentation
    protected fun setVisible() {
        this.view.visibility = View.VISIBLE
    }

    /**
     * Show exit animation.
     *
     * @since 1.0.0
     */
    protected fun showExitAnimation() = this.exitAnimation.run()

    /**
     * Show start animation.
     *
     * @since 1.0.0
     */
    protected fun showStartAnimation() = this.startAnimation.run()

    /**
     * Show update animation.
     *
     * @since 1.0.0
     */
    protected fun showUpdateAnimation() = this.updateAnimation.run()

    // TODO: Add documentation
    protected fun translationX(translation: Float) {
        this.view.translationX = translation
    }

    // TODO: Add documentation
    @Suppress("SameParameterValue")
    protected fun translationY(translation: Float) {
        this.view.translationY = translation
    }
}