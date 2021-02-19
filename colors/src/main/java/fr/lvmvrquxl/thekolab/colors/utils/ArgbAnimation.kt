package fr.lvmvrquxl.thekolab.colors.utils

import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.view.View

internal class ArgbAnimation private constructor(private val target: View) {
    companion object {
        private const val END_COLOR_ERROR_MSG: String = "No end color was provided!"
        private const val PROPERTY_ERROR_MSG: String = "No property was provided!"
        private const val START_COLOR_ERROR_MSG: String = "No start color was provided!"

        fun animate(target: View): ArgbAnimation = ArgbAnimation(target)
    }

    private val evaluator: ArgbEvaluator = ArgbEvaluator()
    private var endColor: Int? = null
    private var property: ArgbAnimationProperty? = null
    private var startColor: Int? = null

    fun start() {
        this.checkFields()
        this.buildAnimator().start()
    }

    fun withEndColor(endColor: Int): ArgbAnimation {
        this.endColor = endColor
        return this
    }

    fun withProperty(property: ArgbAnimationProperty): ArgbAnimation {
        this.property = property
        return this
    }

    fun withStartColor(startColor: Int): ArgbAnimation {
        this.startColor = startColor
        return this
    }

    private fun buildAnimator(): ObjectAnimator = ObjectAnimator.ofObject(
        this.target,
        this.property?.value,
        this.evaluator,
        this.startColor,
        this.endColor
    )

    private fun checkFields() {
        if (null == this.property) throw NoSuchElementException(PROPERTY_ERROR_MSG)
        if (null == this.startColor) throw NoSuchElementException(START_COLOR_ERROR_MSG)
        if (null == this.endColor) throw NoSuchElementException(END_COLOR_ERROR_MSG)
    }
}