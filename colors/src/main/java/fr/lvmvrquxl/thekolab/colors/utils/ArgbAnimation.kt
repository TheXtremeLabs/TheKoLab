package fr.lvmvrquxl.thekolab.colors.utils

import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.view.View
import kotlinx.coroutines.Runnable

internal class ArgbAnimation private constructor(private val target: View) : Runnable {
    companion object {
        private const val END_COLOR_ERROR_MSG: String = "No end color was provided!"
        private const val PROPERTY_ERROR_MSG: String = "No property was provided!"
        private const val START_COLOR_ERROR_MSG: String = "No start color was provided!"

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

    fun endColor(endColor: Int) {
        this.endColor = endColor
    }

    fun property(property: ArgbAnimationProperty) {
        this.propertyValue = property
    }

    fun startColor(startColor: Int) {
        this.startColor = startColor
    }

    private fun checkFields() {
        if (null == this.propertyValue) throw NoSuchElementException(PROPERTY_ERROR_MSG)
        if (null == this.startColor) throw NoSuchElementException(START_COLOR_ERROR_MSG)
        if (null == this.endColor) throw NoSuchElementException(END_COLOR_ERROR_MSG)
    }
}