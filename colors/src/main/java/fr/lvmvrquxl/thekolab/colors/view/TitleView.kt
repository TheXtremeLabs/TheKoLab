package fr.lvmvrquxl.thekolab.colors.view

import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textview.MaterialTextView
import fr.lvmvrquxl.thekolab.colors.model.Color
import fr.lvmvrquxl.thekolab.colors.utils.Animation
import fr.lvmvrquxl.thekolab.colors.utils.ArgbAnimation
import fr.lvmvrquxl.thekolab.colors.utils.ArgbAnimationProperty
import fr.lvmvrquxl.thekolab.shared.view.LifecycleView

internal class TitleView private constructor(
    private val activity: AppCompatActivity,
    private val view: MaterialTextView
) : ColorsAnimatedView(activity) {
    companion object {
        private const val EXIT_ANIMATION_DELAY: Long = 500
        private const val START_ANIMATION_DELAY: Long = 500

        fun create(activity: AppCompatActivity, view: MaterialTextView): LifecycleView =
            TitleView(activity, view)
    }

    override fun showExitAnimation() {
        Animation.create(this.activity, this.view)
            .medium()
            .emptyAlpha()
            .delay(EXIT_ANIMATION_DELAY)
            .start()
    }

    override fun showStartAnimation() {
        this.view.apply {
            this.alpha = 0f
            super@TitleView.color?.let { color: Color -> this.setTextColor(color.value) }
        }
        Animation.create(this.activity, this.view)
            .medium()
            .delay(START_ANIMATION_DELAY)
            .start()
    }

    override fun showUpdateAnimation(): Unit? =
        super.viewModel.previousColor()?.let { previousColor: Color ->
            super.color?.let { color: Color ->
                ArgbAnimation.animate(this.view)
                    .withProperty(ArgbAnimationProperty.TEXT_COLOR)
                    .withStartColor(previousColor.value)
                    .withEndColor(color.value)
                    .start()
            }
        }
}