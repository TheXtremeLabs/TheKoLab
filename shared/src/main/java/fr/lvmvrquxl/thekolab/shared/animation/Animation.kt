package fr.lvmvrquxl.thekolab.shared.animation

import android.animation.AnimatorListenerAdapter
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Runnable

/**
 * Animation utilities.
 *
 * This class should be used for animating given view in an activity.
 *
 * @param activity Activity for retrieving animation's duration from resources
 * @param target View to animate
 *
 * @since 1.0.0
 *
 * @see AppCompatActivity
 * @see Runnable
 * @see View
 */
class Animation private constructor(
    private val activity: AppCompatActivity,
    private val target: View
) : Runnable {
    companion object {
        /**
         * Create a new instance of animation.
         *
         * @param activity Activity for retrieving animation's duration from resources
         * @param target View to animate
         *
         * @return New instance of animation
         *
         * @since 1.0.0
         *
         * @see AppCompatActivity
         * @see View
         */
        fun animate(activity: AppCompatActivity, target: View): Animation =
            Animation(activity, target)
    }

    private val longDuration: Long =
        this.activity.resources.getInteger(android.R.integer.config_longAnimTime).toLong()
    private val mediumDuration: Long =
        this.activity.resources.getInteger(android.R.integer.config_mediumAnimTime).toLong()
    private val shortDuration: Long =
        this.activity.resources.getInteger(android.R.integer.config_shortAnimTime).toLong()

    private var alpha: Float = 1f
    private var delay: Long = 0
    private var duration: Long = this.shortDuration
    private var onEnd: () -> Unit = {}
    private var onStart: () -> Unit = {}
    private var translationXBy: Float = 0f

    override fun run() = this.target.animate()
        .alpha(this.alpha)
        .setDuration(this.duration)
        .setStartDelay(this.delay)
        .setListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: android.animation.Animator?) {
                this@Animation.onEnd()
            }
        })
        .translationXBy(this.translationXBy)
        .withStartAction(this.onStart)
        .start()

    /**
     * Set the start delay of the animation.
     *
     * When this method is not used before running the animation, the default value of the start
     * delay is `0`.
     *
     * @param delay New start delay
     *
     * @since 1.0.0
     */
    fun delay(delay: Long) {
        this.delay = delay
    }

    /**
     * Set the target's alpha to `0f`.
     *
     * When this method is not used before running the animation, the default value of the target's
     * alpha is `1f`.
     *
     * @since 1.0.0
     */
    fun emptyAlpha() {
        this.alpha = 0f
    }

    // TODO: Add documentation
    fun longDuration() {
        this.duration = this.longDuration
    }

    /**
     * Set the animation's duration to medium, corresponding to 400 milliseconds.
     *
     * When this method is not used before running the animation, the default animation's duration
     * is short, corresponding to 200 milliseconds.
     *
     * @since 1.0.0
     */
    fun mediumDuration() {
        this.duration = this.mediumDuration
    }

    /**
     * Set the callback that should be executed after the animation.
     *
     * @param callback Callback to execute
     *
     * @since 1.0.0
     */
    fun onEnd(callback: () -> Unit) {
        this.onEnd = callback
    }

    // TODO: Add documentation
    fun onStart(callback: () -> Unit) {
        this.onStart = callback
    }

    // TODO: Add documentation
    fun translationXBy(translation: Float) {
        this.translationXBy = translation
    }
}