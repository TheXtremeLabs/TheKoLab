package fr.lvmvrquxl.thekolab.colors.view

import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textview.MaterialTextView
import fr.lvmvrquxl.thekolab.colors.model.Color
import fr.lvmvrquxl.thekolab.colors.utils.Animation
import fr.lvmvrquxl.thekolab.colors.utils.ArgbAnimation
import fr.lvmvrquxl.thekolab.colors.utils.ArgbAnimationProperty
import fr.lvmvrquxl.thekolab.shared.view.LifecycleView
import kotlinx.coroutines.Runnable

internal class TitleView private constructor(
    private val activity: AppCompatActivity,
    private val view: MaterialTextView
) : ColorsAnimatedView(activity, view) {
    companion object {
        private const val EXIT_ANIMATION_DELAY: Long = 500
        private const val START_ANIMATION_DELAY: Long = 500

        fun create(activity: AppCompatActivity, view: MaterialTextView): LifecycleView =
            TitleView(activity, view)
    }

    override val exitAnimation: Runnable
        get() = Animation.animate(this.activity, this.view).apply {
            this.medium()
            this.emptyAlpha()
            this.delay(EXIT_ANIMATION_DELAY)
        }
    override val startAnimation: Runnable
        get() = Animation.animate(this.activity, this.view).apply {
            this.medium()
            this.delay(START_ANIMATION_DELAY)
        }
    override val updateAnimation: Runnable
        get() = ArgbAnimation.animate(this.view).apply {
            this.property(ArgbAnimationProperty.TEXT_COLOR)
            super.viewModel.previousColor()?.let { color: Color -> this.startColor(color.value) }
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