package fr.lvmvrquxl.thekolab.colors.view.content

import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import fr.lvmvrquxl.thekolab.colors.model.color.Color
import fr.lvmvrquxl.thekolab.colors.utils.animation.ArgbAnimationProperty
import fr.lvmvrquxl.thekolab.colors.view.ColorsAnimatedView
import fr.lvmvrquxl.thekolab.shared.view.LifecycleView
import kotlinx.coroutines.Runnable

/**
 * View of the change colors button.
 *
 * @param activity Instance of the colors activity
 * @param view Binding of the button
 *
 * @since 1.0.0
 *
 * @see AppCompatActivity
 * @see ColorsAnimatedView
 * @see MaterialButton
 */
internal class ChangeColorsView private constructor(
    activity: AppCompatActivity,
    private val view: MaterialButton
) : ColorsAnimatedView(activity, view) {
    companion object {
        private const val START_ANIMATION_DELAY: Long = 750

        /**
         * Create an instance of the change colors button.
         *
         * @param activity Instance of the colors activity
         * @param view Binding of the button
         *
         * @return New instance of the button
         *
         * @since 1.0.0
         *
         * @see AppCompatActivity
         * @see LifecycleView
         * @see MaterialButton
         */
        fun create(activity: AppCompatActivity, view: MaterialButton): LifecycleView =
            ChangeColorsView(activity, view)
    }

    override val exitAnimation: Runnable
        get() = super.mediumAnimation.apply { this.emptyAlpha() }
    override val startAnimation: Runnable
        get() = super.mediumAnimation.apply {
            this.delay(START_ANIMATION_DELAY)
            this.onEnd { super.enableClick() }
        }
    override val updateAnimation: Runnable
        get() = super.argbAnimation.apply {
            this.property(ArgbAnimationProperty.BACKGROUND_COLOR)
            super.viewModel.previousColor?.let { color: Color -> this.startColor(color.value) }
            super.color?.let { color: Color -> this.endColor(color.value) }
        }

    override fun onStart() = this.setClickListener()

    override fun showExitAnimation() {
        super.disableClick()
        super.showExitAnimation()
    }

    override fun showStartAnimation() {
        super.disableClick()
        super.hide()
        this.setBackgroundColor()
        super.showStartAnimation()
    }

    private fun setBackgroundColor() =
        super.color?.let { color: Color -> this.view.setBackgroundColor(color.value) }

    private fun setClickListener() = this.view.setOnClickListener { super.viewModel.updateColor() }
}