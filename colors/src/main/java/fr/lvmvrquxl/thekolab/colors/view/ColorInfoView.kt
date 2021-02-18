package fr.lvmvrquxl.thekolab.colors.view

import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textview.MaterialTextView
import fr.lvmvrquxl.thekolab.colors.model.Color
import fr.lvmvrquxl.thekolab.colors.utils.Animation
import fr.lvmvrquxl.thekolab.shared.view.LifecycleView

internal class ColorInfoView private constructor(
    private val activity: AppCompatActivity,
    private val view: MaterialTextView
) : ColorsAnimatedView(activity) {
    companion object {
        private const val EXIT_ANIMATION_DELAY: Long = 250
        private const val START_ANIMATION_DELAY: Long = 250

        fun create(activity: AppCompatActivity, view: MaterialTextView): LifecycleView =
            ColorInfoView(activity, view)
    }

    override fun showExitAnimation() = Animation.create(this.activity, this.view)
        .medium()
        .emptyAlpha()
        .delay(EXIT_ANIMATION_DELAY)
        .start()

    override fun showStartAnimation() {
        super.color?.let { color: Color ->
            this.view.apply {
                this.alpha = 0f
                this.text = color.name
                this.setTextColor(color.value)
            }
        }
        Animation.create(this.activity, this.view)
            .medium()
            .delay(START_ANIMATION_DELAY)
            .start()
    }

    override fun showUpdateAnimation() = Animation.create(this.activity, this.view)
        .emptyAlpha()
        .onEnd {
            super.color?.let { color: Color ->
                this.view.apply {
                    this.text = color.name
                    this.setTextColor(color.value)
                }
            }
            Animation.create(this.activity, this.view)
                .medium()
                .start()
        }.start()
}