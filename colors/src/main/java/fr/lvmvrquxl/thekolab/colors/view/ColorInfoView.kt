package fr.lvmvrquxl.thekolab.colors.view

import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textview.MaterialTextView
import fr.lvmvrquxl.thekolab.colors.model.Color
import fr.lvmvrquxl.thekolab.colors.utils.Animation
import fr.lvmvrquxl.thekolab.shared.view.LifecycleView
import kotlinx.coroutines.Runnable

internal class ColorInfoView private constructor(
    private val activity: AppCompatActivity,
    private val view: MaterialTextView
) : ColorsAnimatedView(activity, view) {
    companion object {
        private const val EXIT_ANIMATION_DELAY: Long = 250
        private const val START_ANIMATION_DELAY: Long = 250

        fun create(activity: AppCompatActivity, view: MaterialTextView): LifecycleView =
            ColorInfoView(activity, view)
    }

    override val exitAnimation: Runnable
        get() = Animation.animate(this.activity, this.view).apply {
            this.medium()
            this.emptyAlpha()
            this.delay(EXIT_ANIMATION_DELAY)
        }
    override val startAnimation: Runnable
        get() = Animation.animate(this.activity, this.view).apply {
            this.medium()
            this.delay(START_ANIMATION_DELAY)
        }
    override val updateAnimation: Runnable
        get() = Animation.animate(this.activity, this.view).apply {
            this.emptyAlpha()
            this.onEnd {
                this@ColorInfoView.setText()
                this@ColorInfoView.setTextColor()
                this@ColorInfoView.updateEndAnimation.run()
            }
        }
    private val updateEndAnimation: Runnable
        get() = Animation.animate(this.activity, this.view).apply { this.medium() }

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