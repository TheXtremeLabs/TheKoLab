package fr.lvmvrquxl.thekolab.colors.view.content

import com.google.android.material.button.MaterialButton
import fr.lvmvrquxl.thekolab.colors.model.color.Color
import fr.lvmvrquxl.thekolab.colors.view.ColorsAnimatedView
import fr.lvmvrquxl.thekolab.shared.activity.Activity
import fr.lvmvrquxl.thekolab.shared.animation.ArgbAnimationProperty
import kotlinx.coroutines.Runnable

/**
 * View of the change colors button.
 *
 * @since 1.0.0
 */
internal class ChangeColorsView private constructor(
    private val activity: Activity,
    private val view: MaterialButton
) : ColorsAnimatedView(activity, view) {
    companion object {
        private const val START_ANIMATION_DELAY: Long = 750

        /**
         * Observe the given activity's lifecycle.
         *
         * @param activity Colors activity
         * @param view View corresponding to the change colors button
         *
         * @since 2.0.0
         */
        fun observe(activity: Activity, view: MaterialButton) =
            ChangeColorsView(activity, view).let { v: ChangeColorsView -> activity.addObserver(v) }
    }

    override val exitAnimation: Runnable
        get() {
            super.disableClick()
            return super.mediumAnimation.apply { this.emptyAlpha() }
        }

    override val startAnimation: Runnable
        get() = super.mediumAnimation.apply {
            this.delay(START_ANIMATION_DELAY)
            this.onEnd { super.enableClick() }
        }

    override val updateAnimation: Runnable
        get() = super.argbAnimation.apply {
            this.property(ArgbAnimationProperty.BACKGROUND_COLOR)
            super.viewModel?.previousColor?.let { color: Color -> this.startColor(color.value) }
            super.color?.let { color: Color -> this.endColor(color.value) }
        }

    override fun onDestroy() {
        this.stopActivityObservation()
        super.onDestroy()
    }

    override fun onResume() {
        super.onResume()
        super.disableClick()
        this.setBackgroundColor()
        this.setClickListener()
    }

    private fun setBackgroundColor() =
        super.color?.let { color: Color -> this.view.setBackgroundColor(color.value) }

    private fun setClickListener() = this.view.setOnClickListener { super.viewModel?.updateColor() }

    private fun stopActivityObservation() = this.activity.removeObserver(this)
}