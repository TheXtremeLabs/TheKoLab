package fr.lvmvrquxl.thekolab.colors.view.toolbar

import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textview.MaterialTextView
import fr.lvmvrquxl.thekolab.colors.model.color.Color
import fr.lvmvrquxl.thekolab.colors.utils.animation.ArgbAnimationProperty
import fr.lvmvrquxl.thekolab.colors.view.ColorsAnimatedView
import fr.lvmvrquxl.thekolab.shared.view.LifecycleView
import kotlinx.coroutines.Runnable

internal class TitleView private constructor(
    activity: AppCompatActivity,
    private val view: MaterialTextView
) : ColorsAnimatedView(activity, view) {
    companion object {
        private const val EXIT_ANIMATION_DELAY: Long = 500
        private const val START_ANIMATION_DELAY: Long = 500

        fun create(activity: AppCompatActivity, view: MaterialTextView): LifecycleView =
            TitleView(activity, view)
    }

    override val exitAnimation: Runnable
        get() = super.mediumAnimation.apply {
            this.emptyAlpha()
            this.delay(EXIT_ANIMATION_DELAY)
        }
    override val startAnimation: Runnable
        get() = super.mediumAnimation.apply { this.delay(START_ANIMATION_DELAY) }
    override val updateAnimation: Runnable
        get() = super.argbAnimation.apply {
            this.property(ArgbAnimationProperty.TEXT_COLOR)
            super.viewModel.previousColor?.let { color: Color -> this.startColor(color.value) }
            super.color?.let { color: Color -> this.endColor(color.value) }
        }

    override fun showStartAnimation() {
        super.hide()
        this.setTextColor()
        super.showStartAnimation()
    }

    private fun setTextColor() =
        super.color?.let { color: Color -> this.view.setTextColor(color.value) }
}