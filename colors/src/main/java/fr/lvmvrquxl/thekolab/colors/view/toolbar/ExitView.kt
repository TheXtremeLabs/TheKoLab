package fr.lvmvrquxl.thekolab.colors.view.toolbar

import com.google.android.material.imageview.ShapeableImageView
import fr.lvmvrquxl.thekolab.colors.model.color.Color
import fr.lvmvrquxl.thekolab.colors.view.ColorsAnimatedView
import fr.lvmvrquxl.thekolab.shared.activity.Activity
import fr.lvmvrquxl.thekolab.shared.animation.ArgbAnimationProperty
import kotlinx.coroutines.Runnable

/**
 * View of the exit button.
 *
 * @since 1.0.0
 */
internal class ExitView private constructor(
    private val activity: Activity,
    private val view: ShapeableImageView
) : ColorsAnimatedView(activity, view) {
    companion object {
        private const val EXIT_ANIMATION_DELAY: Long = 750
        private const val START_ANIMATION_DELAY: Long = 1000

        /**
         * Observe the given activity's lifecycle.
         *
         * @param activity Colors activity
         * @param view View corresponding to the exit button
         *
         * @since 2.0.0
         */
        fun observe(activity: Activity, view: ShapeableImageView) =
            ExitView(activity, view).let { v: ExitView -> activity.addObserver(v) }
    }

    override val exitAnimation: Runnable
        get() {
            super.disableClick()
            return super.mediumAnimation.apply {
                this.emptyAlpha()
                this.delay(EXIT_ANIMATION_DELAY)
                this.onEnd { super.viewModel.closeActivity() }
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
            super.viewModel.previousColor?.let { color: Color -> this.startColor(color.value) }
            super.color?.let { color: Color -> this.endColor(color.value) }
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
        super.color?.let { color: Color -> this.view.setColorFilter(color.value) }

    private fun setListener() = this.view.setOnClickListener { super.viewModel.onBackPressed() }

    private fun stopActivityObservation() = this.activity.removeObserver(this)
}