package fr.lvmvrquxl.thekolab.colors.view

import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import fr.lvmvrquxl.thekolab.colors.model.Color
import fr.lvmvrquxl.thekolab.colors.utils.Animation
import fr.lvmvrquxl.thekolab.colors.utils.ArgbAnimation
import fr.lvmvrquxl.thekolab.colors.utils.ArgbAnimationProperty
import fr.lvmvrquxl.thekolab.shared.view.LifecycleView

internal class ChangeColorsView private constructor(
    private val activity: AppCompatActivity,
    private val view: MaterialButton
) : ColorsAnimatedView(activity) {
    companion object {
        private const val START_ANIMATION_DELAY: Long = 750

        fun create(activity: AppCompatActivity, view: MaterialButton): LifecycleView =
            ChangeColorsView(activity, view)
    }

    override fun onStart() = this.setClickListener()

    override fun showExitAnimation() {
        this.view.isClickable = false
        Animation.create(this.activity, this.view)
            .medium()
            .emptyAlpha()
            .start()
    }

    override fun showStartAnimation() {
        this.view.apply {
            this.alpha = 0f
            this.isClickable = false
            super@ChangeColorsView.color?.let { color: Color ->
                this.setBackgroundColor(color.value)
            }
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
                ArgbAnimation.animate(this.view)
                    .withProperty(ArgbAnimationProperty.BACKGROUND_COLOR)
                    .withStartColor(previousColor.value)
                    .withEndColor(color.value)
                    .start()
            }
        }

    private fun setClickListener() = this.view.setOnClickListener { super.viewModel.updateColor() }
}