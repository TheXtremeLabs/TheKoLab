package fr.lvmvrquxl.thekolab.colors.utils

import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.view.View

internal object ArgbAnimation {
    fun show(target: View, property: Property, startColor: Int, endColor: Int) =
        ObjectAnimator.ofObject(
            target,
            property.value,
            ArgbEvaluator(),
            startColor,
            endColor
        ).start()

    enum class Property(val value: String) {
        BACKGROUND_COLOR("backgroundColor"),
        COLOR_FILTER("colorFilter"),
        TEXT_COLOR("textColor")
    }
}