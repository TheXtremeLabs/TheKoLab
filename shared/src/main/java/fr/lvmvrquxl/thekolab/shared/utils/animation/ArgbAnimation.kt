package fr.lvmvrquxl.thekolab.shared.utils.animation

import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.view.View
import kotlinx.coroutines.Runnable

/**
 * ARGB animation utilities.
 *
 * This class should be used for animating property's colors of a given view.
 *
 * @param target View to animate
 *
 * @since 1.0.0
 *
 * @see Runnable
 * @see View
 */
class ArgbAnimation private constructor(private val target: View) : Runnable {
    companion object {
        private const val END_COLOR_ERROR_MSG: String = "No end color was provided!"
        private const val PROPERTY_ERROR_MSG: String = "No property was provided!"
        private const val START_COLOR_ERROR_MSG: String = "No start color was provided!"

        /**
         * Create a new instance of ARGB animation.
         *
         * @param target View to animate
         *
         * @return New instance of ARGB animation
         *
         * @since 1.0.0
         *
         * @see View
         */
        fun animate(target: View): ArgbAnimation = ArgbAnimation(target)
    }

    private val animator: ObjectAnimator
        get() = ObjectAnimator.ofObject(
            this.target,
            this.propertyValue?.value,
            this.evaluator,
            this.startColor,
            this.endColor
        )

    private val evaluator: ArgbEvaluator = ArgbEvaluator()
    private var endColor: Int? = null
    private var propertyValue: ArgbAnimationProperty? = null
    private var startColor: Int? = null

    override fun run() {
        this.checkFields()
        this.animator.start()
    }

    /**
     * Set the end color.
     *
     * If no end color is provided, trying to run the current animation will throw a
     * [NoSuchElementException].
     *
     * @param endColor End color
     *
     * @since 1.0.0
     */
    fun endColor(endColor: Int) {
        this.endColor = endColor
    }

    /**
     * Set the property to animate.
     *
     * If no property is provided, trying to run the current animation will throw a
     * [NoSuchElementException].
     *
     * @param property Property to animate
     *
     * @since 1.0.0
     *
     * @see ArgbAnimationProperty
     */
    fun property(property: ArgbAnimationProperty) {
        this.propertyValue = property
    }

    /**
     * Set the start color.
     *
     * If no start color is provided, trying to run the current animation will throw a
     * [NoSuchElementException].
     *
     * @param startColor Start color
     *
     * @since 1.0.0
     */
    fun startColor(startColor: Int) {
        this.startColor = startColor
    }

    private fun checkFields() {
        if (null == this.propertyValue) throw NoSuchElementException(PROPERTY_ERROR_MSG)
        if (null == this.startColor) throw NoSuchElementException(START_COLOR_ERROR_MSG)
        if (null == this.endColor) throw NoSuchElementException(END_COLOR_ERROR_MSG)
    }
}