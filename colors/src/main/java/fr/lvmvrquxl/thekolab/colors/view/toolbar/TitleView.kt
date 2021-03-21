package fr.lvmvrquxl.thekolab.colors.view.toolbar

import com.google.android.material.textview.MaterialTextView
import fr.lvmvrquxl.thekolab.colors.view.ColorsAnimatedView
import fr.lvmvrquxl.thekolab.shared.activity.ActivityReference
import fr.lvmvrquxl.thekolab.shared.animation.ArgbAnimationProperty
import kotlinx.coroutines.Runnable

/**
 * View of the title.
 */
internal class TitleView private constructor(
    private val activityReference: ActivityReference,
    private val view: MaterialTextView
) : ColorsAnimatedView(activityReference, view) {
    companion object {
        private const val EXIT_ANIMATION_DELAY: Long = 500
        private const val START_ANIMATION_DELAY: Long = 500

        /**
         * Observe the given activity's lifecycle.
         *
         * @param activityReference Reference of the colors activity
         * @param view View corresponding to the activity's title
         */
        fun observe(activityReference: ActivityReference, view: MaterialTextView) {
            val titleView = TitleView(activityReference, view)
            activityReference.get()?.addObserver(titleView)
        }
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
            super.viewModel?.previousColor?.value?.let { value: Int -> this.startColor(value) }
            super.color?.value?.let { value: Int -> this.endColor(value) }
        }

    override fun onDestroy() {
        this.stopActivityObservation()
        super.onDestroy()
    }

    override fun onResume() {
        super.onResume()
        this.setTextColor()
    }

    private fun setTextColor() =
        super.color?.value?.let { value: Int -> this.view.setTextColor(value) }

    private fun stopActivityObservation() {
        this.activityReference.get()?.removeObserver(this)
    }
}