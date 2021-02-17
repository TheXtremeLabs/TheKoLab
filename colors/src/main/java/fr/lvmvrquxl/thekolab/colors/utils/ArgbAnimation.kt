package fr.lvmvrquxl.thekolab.colors.utils

import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.view.View

internal class ArgbAnimation private constructor(
    private val target: View,
    private val property: Property,
    private val startColor: Int,
    private val endColor: Int
) {
    companion object {
        fun show(target: View, property: Property, startColor: Int, endColor: Int) =
            ArgbAnimation(target, property, startColor, endColor).start()
    }

    private fun start() = ObjectAnimator.ofObject(
        this.target,
        this.property.value,
        ArgbEvaluator(),
        this.startColor,
        this.endColor
    ).start()

    enum class Property(val value: String) {
        BACKGROUND_COLOR("backgroundColor"),
        COLOR_FILTER("colorFilter"),
        TEXT_COLOR("textColor")
    }
}