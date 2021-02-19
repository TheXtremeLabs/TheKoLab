package fr.lvmvrquxl.thekolab.colors.view.toolbar

import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.imageview.ShapeableImageView
import fr.lvmvrquxl.thekolab.colors.model.color.Color
import fr.lvmvrquxl.thekolab.colors.utils.animation.ArgbAnimationProperty
import fr.lvmvrquxl.thekolab.colors.view.ColorsAnimatedView
import fr.lvmvrquxl.thekolab.shared.view.LifecycleView
import kotlinx.coroutines.Runnable

internal class ExitView private constructor(
    activity: AppCompatActivity,
    private val view: ShapeableImageView
) : ColorsAnimatedView(activity, view) {
    companion object {
        private const val EXIT_ANIMATION_DELAY: Long = 750
        private const val START_ANIMATION_DELAY: Long = 1000

        fun create(activity: AppCompatActivity, view: ShapeableImageView): LifecycleView =
            ExitView(activity, view)
    }

    override val exitAnimation: Runnable
        get() = super.mediumAnimation.apply {
            this.emptyAlpha()
            this.delay(EXIT_ANIMATION_DELAY)
            this.onEnd { super.viewModel.destroyActivity() }
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

    override fun onStart() = this.setListener()

    override fun showExitAnimation() {
        super.disableClick()
        super.showExitAnimation()
    }

    override fun showStartAnimation() {
        super.hide()
        super.disableClick()
        this.setColorFilter()
        super.showStartAnimation()
    }

    private fun setColorFilter() =
        super.color?.let { color: Color -> this.view.setColorFilter(color.value) }

    private fun setListener() = this.view.setOnClickListener { super.viewModel.onBackPressed() }
}