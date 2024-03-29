package fr.lvmvrquxl.thekolab.colors.view.content

import com.google.android.material.button.MaterialButton
import fr.lvmvrquxl.thekolab.colors.view.ColorsAnimatedView
import fr.lvmvrquxl.thekolab.shared.activity.ActivityReference
import fr.lvmvrquxl.thekolab.shared.animation.ArgbAnimationProperty
import kotlinx.coroutines.Runnable

/**
 * View of the change colors button.
 */
internal class ChangeColorsView private constructor(
    private val activityReference: ActivityReference,
    private val view: MaterialButton
) : ColorsAnimatedView(activityReference, view) {
    companion object {
        private const val START_ANIMATION_DELAY: Long = 750

        /**
         * Observe the given activity's lifecycle.
         *
         * @param activityReference Reference of the colors activity
         * @param view View corresponding to the change colors button
         */
        fun observe(activityReference: ActivityReference, view: MaterialButton) {
            val changeColorsView = ChangeColorsView(activityReference, view)
            activityReference.get()?.addObserver(changeColorsView)
        }
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
            super.viewModel?.previousColor?.value?.let { value: Int -> this.startColor(value) }
            super.color?.value?.let { value: Int -> this.endColor(value) }
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
        super.color?.value?.let { value: Int -> this.view.setBackgroundColor(value) }

    private fun setClickListener() =
        this.view.setOnClickListener { super.viewModel?.changeColors() }

    private fun stopActivityObservation() = this.activityReference.get()?.removeObserver(this)
}