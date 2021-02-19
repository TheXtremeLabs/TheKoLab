package fr.lvmvrquxl.thekolab.colors.view

import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.imageview.ShapeableImageView
import fr.lvmvrquxl.thekolab.colors.model.Color
import fr.lvmvrquxl.thekolab.colors.utils.Animation
import fr.lvmvrquxl.thekolab.colors.utils.ArgbAnimation
import fr.lvmvrquxl.thekolab.colors.utils.ArgbAnimationProperty
import fr.lvmvrquxl.thekolab.shared.view.LifecycleView

internal class ExitView private constructor(
    private val activity: AppCompatActivity,
    private val view: ShapeableImageView
) : ColorsAnimatedView(activity) {
    companion object {
        private const val EXIT_ANIMATION_DELAY: Long = 750
        private const val START_ANIMATION_DELAY: Long = 1000

        fun create(activity: AppCompatActivity, view: ShapeableImageView): LifecycleView =
            ExitView(activity, view)
    }

    override fun onStart() = this.setListener()

    override fun showExitAnimation() {
        this.view.isClickable = false
        Animation.create(this.activity, this.view)
            .medium()
            .emptyAlpha()
            .delay(EXIT_ANIMATION_DELAY)
            .onEnd { super.viewModel.close() }
            .start()
    }

    override fun showStartAnimation() {
        this.view.apply {
            this.alpha = 0f
            this.isClickable = false
            super@ExitView.color?.let { color: Color -> this.setColorFilter(color.value) }
        }
        Animation.create(this.activity, this.view)
            .medium()
            .delay(START_ANIMATION_DELAY)
            .onEnd { this.view.isClickable = true }
            .start()
    }

    override fun showUpdateAnimation() =
        super.viewModel.previousColor()?.let { previousColor: Color ->
            super.color?.let { color: Color ->
                ArgbAnimation.show(
                    this.view,
                    ArgbAnimationProperty.COLOR_FILTER,
                    previousColor.value,
                    color.value
                )
            }
        }

    private fun setListener() = this.view.setOnClickListener { super.viewModel.exit() }
}