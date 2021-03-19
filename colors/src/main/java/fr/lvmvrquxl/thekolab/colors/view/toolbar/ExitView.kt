package fr.lvmvrquxl.thekolab.colors.view.toolbar

import com.google.android.material.imageview.ShapeableImageView
import fr.lvmvrquxl.thekolab.colors.view.ColorsAnimatedView
import fr.lvmvrquxl.thekolab.shared.activity.ActivityReference
import fr.lvmvrquxl.thekolab.shared.animation.ArgbAnimationProperty
import kotlinx.coroutines.Runnable

/**
 * View of the exit button.
 */
internal class ExitView private constructor(
    private val activityReference: ActivityReference,
    private val view: ShapeableImageView
) : ColorsAnimatedView(activityReference, view) {
    companion object {
        private const val EXIT_ANIMATION_DELAY: Long = 750
        private const val START_ANIMATION_DELAY: Long = 1000

        /**
         * Observe the given activity's lifecycle.
         *
         * @param activityReference Reference of the colors activity
         * @param view View corresponding to the exit button
         */
        fun observe(activityReference: ActivityReference, view: ShapeableImageView) {
            val exitView = ExitView(activityReference, view)
            activityReference.get()?.addObserver(exitView)
        }
    }

    override val exitAnimation: Runnable
        get() {
            super.disableClick()
            return super.mediumAnimation.apply {
                this.emptyAlpha()
                this.delay(EXIT_ANIMATION_DELAY)
                this.onEnd { super.viewModel?.closeActivity() }
            }
        }

    override val startAnimation: Runnable
        get() = super.mediumAnimation.apply {
            this.delay(START_ANIMATION_DELAY)
            this.onEnd { super.enableClick() }
        }

    override val updateAnimation: Runnable
        get() = super.argbAnimation.apply {
            this.property(ArgbAnimationProperty.COLOR_FILTER)
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
        this.setColorFilter()
        this.setListener()
    }

    private fun setColorFilter() =
        super.color?.value?.let { value: Int -> this.view.setColorFilter(value) }

    private fun setListener() = this.view.setOnClickListener { super.viewModel?.onBackPressed() }

    private fun stopActivityObservation() = this.activityReference.get()?.removeObserver(this)
}