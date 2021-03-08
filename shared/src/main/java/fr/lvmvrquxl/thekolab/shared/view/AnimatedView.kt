package fr.lvmvrquxl.thekolab.shared.view

import android.view.View
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import fr.lvmvrquxl.thekolab.shared.animation.Animation
import kotlinx.coroutines.Runnable

/**
 * Parent of all animated views.
 *
 * @since 1.0.0
 */
abstract class AnimatedView(
    private val activity: AppCompatActivity,
    private val view: View
) : LifecycleView() {
    /**
     * Animation executed when the user closes the activity.
     *
     * @since 1.0.0
     */
    protected open val exitAnimation: Runnable
        get() = this.animation

    /**
     * Animation executed when the activity starts.
     *
     * @since 1.0.0
     */
    protected open val startAnimation: Runnable
        get() = this.animation

    /**
     * Animation executed when displayed data is updated.
     *
     * @since 1.0.0
     */
    protected open val updateAnimation: Runnable
        get() = this.animation

    /**
     * Animation instance for the view.
     *
     * @since 1.0.0
     */
    protected val animation: Animation
        get() = Animation.animate(this.activity, this.view)
            .apply { this@AnimatedView.currentAnimation = this }

    private var currentAnimation: Animation? = null

    @CallSuper
    override fun onCreate() = this.observeViewModel()

    @CallSuper
    override fun onDestroy() {
        this.currentAnimation = null
    }

    @CallSuper
    override fun onPause() {
        this.currentAnimation?.cancel()
    }

    @Deprecated("Will be removed in version 2.0.0")
    protected open fun observeViewModel() {
    }

    /**
     * Hide the current view.
     *
     * @since 1.0.0
     */
    protected fun hide() {
        this.view.alpha = 0f
    }

    /**
     * Make the current view visible.
     *
     * @since 2.0.0
     */
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

    /**
     * Set the translation X of the current view.
     *
     * @param translation X value
     *
     * @since 2.0.0
     */
    protected fun translationX(translation: Float) {
        this.view.translationX = translation
    }

    /**
     * Set the translation Y of the current view.
     *
     * @param translation Y value
     *
     * @since 2.0.0
     */
    @Suppress("SameParameterValue")
    protected fun translationY(translation: Float) {
        this.view.translationY = translation
    }
}