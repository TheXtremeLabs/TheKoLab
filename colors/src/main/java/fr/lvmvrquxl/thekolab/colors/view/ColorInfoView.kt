package fr.lvmvrquxl.thekolab.colors.view

import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textview.MaterialTextView
import fr.lvmvrquxl.thekolab.colors.model.Color
import fr.lvmvrquxl.thekolab.shared.view.LifecycleView
import kotlinx.coroutines.Runnable

internal class ColorInfoView private constructor(
    activity: AppCompatActivity,
    private val view: MaterialTextView
) : ColorsAnimatedView(activity, view) {
    companion object {
        private const val EXIT_ANIMATION_DELAY: Long = 250
        private const val START_ANIMATION_DELAY: Long = 250

        fun create(activity: AppCompatActivity, view: MaterialTextView): LifecycleView =
            ColorInfoView(activity, view)
    }

    override val exitAnimation: Runnable
        get() = super.mediumAnimation.apply {
            this.emptyAlpha()
            this.delay(EXIT_ANIMATION_DELAY)
        }
    override val startAnimation: Runnable
        get() = super.mediumAnimation.apply { this.delay(START_ANIMATION_DELAY) }
    override val updateAnimation: Runnable
        get() = super.animation.apply {
            this.emptyAlpha()
            this.onEnd {
                this@ColorInfoView.setText()
                this@ColorInfoView.setTextColor()
                this@ColorInfoView.mediumAnimation.run()
            }
        }

    override fun showStartAnimation() {
        super.hide()
        this.setText()
        this.setTextColor()
        super.showStartAnimation()
    }

    private fun setText() = super.color?.let { color: Color -> this.view.text = color.name }

    private fun setTextColor() =
        super.color?.let { color: Color -> this.view.setTextColor(color.value) }
}