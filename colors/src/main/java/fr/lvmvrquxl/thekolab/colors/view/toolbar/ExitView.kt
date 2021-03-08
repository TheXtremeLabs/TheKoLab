package fr.lvmvrquxl.thekolab.colors.view.toolbar

import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.imageview.ShapeableImageView
import fr.lvmvrquxl.thekolab.colors.model.color.Color
import fr.lvmvrquxl.thekolab.shared.animation.ArgbAnimationProperty
import fr.lvmvrquxl.thekolab.colors.view.ColorsAnimatedView
import fr.lvmvrquxl.thekolab.shared.view.LifecycleView
import kotlinx.coroutines.Runnable

/**
 * View of the exit button.
 *
 * @param activity Instance of the colors activity
 * @param view Binding of the button
 *
 * @since 1.0.0
 *
 * @see AppCompatActivity
 * @see ColorsAnimatedView
 * @see ShapeableImageView
 */
internal class ExitView private constructor(
    activity: AppCompatActivity,
    private val view: ShapeableImageView
) : ColorsAnimatedView(activity, view) {
    companion object {
        private const val EXIT_ANIMATION_DELAY: Long = 750
        private const val START_ANIMATION_DELAY: Long = 1000

        /**
         * Create an instance of the exit button's view.
         *
         * @param activity Instance of the colors activity
         * @param view Binding of the button
         *
         * @return New instance of the view
         *
         * @since 1.0.0
         *
         * @see AppCompatActivity
         * @see LifecycleView
         * @see ShapeableImageView
         */
        fun create(activity: AppCompatActivity, view: ShapeableImageView): LifecycleView =
            ExitView(activity, view)
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

    override fun onResume() {
        super.onResume()
        super.disableClick()
        this.setColorFilter()
        this.setListener()
    }

    private fun setColorFilter() =
        super.color?.let { color: Color -> this.view.setColorFilter(color.value) }

    private fun setListener() = this.view.setOnClickListener { super.viewModel.onBackPressed() }
}