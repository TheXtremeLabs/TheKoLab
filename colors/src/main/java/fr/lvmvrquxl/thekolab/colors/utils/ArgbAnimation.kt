package fr.lvmvrquxl.thekolab.colors.utils

import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.view.View

internal object ArgbAnimation {
    fun show(target: View, property: ArgbAnimationProperty, startColor: Int, endColor: Int) =
        ObjectAnimator.ofObject(target, property.value, ArgbEvaluator(), startColor, endColor)
            .start()
}