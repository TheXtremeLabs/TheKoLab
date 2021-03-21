package fr.lvmvrquxl.thekolab.shared.animation

import android.view.View
import android.view.ViewPropertyAnimator
import fr.lvmvrquxl.thekolab.shared.activity.Activity
import fr.lvmvrquxl.thekolab.shared.activity.ActivityReference
import kotlinx.coroutines.Runnable

/**
 * Animation utilities.
 *
 * This class should be used for animating given view in an activity.
 *
 * @since 1.0.0
 */
class Animation private constructor(
    private val activityReference: ActivityReference,
    private val target: View
) : Runnable {
    companion object {
        /**
         * Create a new instance of animation.
         *
         * @param activityReference Activity's reference
         * @param target View to animate
         *
         * @return New instance of animation
         *
         * @since 2.0.0
         */
        fun animate(activityReference: ActivityReference, target: View): Animation =
            Animation(activityReference, target)
    }

    private val longDuration: Long?
        get() = this.activityReference.get()?.let { activity: Activity ->
            activity.resources.getInteger(android.R.integer.config_longAnimTime).toLong()
        }
    private val mediumDuration: Long?
        get() = this.activityReference.get()?.let { activity: Activity ->
            activity.resources.getInteger(android.R.integer.config_mediumAnimTime).toLong()
        }
    private val shortDuration: Long?
        get() = this.activityReference.get()?.let { activity: Activity ->
            activity.resources.getInteger(android.R.integer.config_shortAnimTime).toLong()
        }

    private var alpha: Float = 1f
    private var currentAnimator: ViewPropertyAnimator? = null
    private var delay: Long = 0
    private var duration: Long? = this.shortDuration
    private var onEnd: () -> Unit = {}
    private var onStart: () -> Unit = {}
    private var translationXBy: Float = 0f
    private var translationYBy: Float = 0f

    override fun run() = this.target.animate().apply {
        this.alpha(this@Animation.alpha)
        this@Animation.duration?.let { value: Long -> this.duration = value }
        this.startDelay = this@Animation.delay
        this.translationXBy(this@Animation.translationXBy)
        this.translationYBy(this@Animation.translationYBy)
        this.withEndAction(this@Animation.onEnd)
        this.withStartAction(this@Animation.onStart)
        this@Animation.backupAnimator(this)
    }.start()

    /**
     * Cancel current animation.
     *
     * @since 2.0.0
     */
    fun cancel() = this.currentAnimator?.cancel()

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

    /**
     * Set the animation's duration to long, corresponding to 500 milliseconds.
     *
     * When this method is not used before running the animation, the default animation's duration
     * is short, corresponding to 200 milliseconds.
     *
     * @since 2.0.0
     */
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
     * Set the callback that should be executed when the animation ends.
     *
     * When this method is not used before running the animation, no action is executed when the
     * animation ends.
     *
     * @param callback Callback to execute
     *
     * @since 1.0.0
     */
    fun onEnd(callback: () -> Unit) {
        this.onEnd = callback
    }

    /**
     * Set the callback that should be executed when the animation starts.
     *
     * When this method is not used before running the animation, no action is executed when
     * the animation starts.
     *
     * @param callback Callback to execute
     *
     * @since 2.0.0
     */
    fun onStart(callback: () -> Unit) {
        this.onStart = callback
    }

    /**
     * Set the translation X to apply during the animation.
     *
     * When this method is not used before running the animation, no translation is applied.
     *
     * @param translation X value to translate by
     *
     * @since 2.0.0
     */
    fun translationXBy(translation: Float) {
        this.translationXBy = translation
    }

    /**
     * Set the translation Y to apply during the animation.
     *
     * When this method is not used before running the animation, no translation is applied.
     *
     * @param translation Y value to translate by
     *
     * @since 2.0.0
     */
    fun translationYBy(translation: Float) {
        this.translationYBy = translation
    }

    private fun backupAnimator(animator: ViewPropertyAnimator) {
        this.currentAnimator = animator
    }
}