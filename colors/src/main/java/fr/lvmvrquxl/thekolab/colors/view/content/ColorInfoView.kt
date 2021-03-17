package fr.lvmvrquxl.thekolab.colors.view.content

import com.google.android.material.textview.MaterialTextView
import fr.lvmvrquxl.thekolab.colors.model.color.Color
import fr.lvmvrquxl.thekolab.colors.view.ColorsAnimatedView
import fr.lvmvrquxl.thekolab.shared.activity.ActivityReference
import kotlinx.coroutines.Runnable

/**
 * View of the color information.
 */
internal class ColorInfoView private constructor(
    private val activityReference: ActivityReference,
    private val view: MaterialTextView
) : ColorsAnimatedView(activityReference, view) {
    companion object {
        private const val EXIT_ANIMATION_DELAY: Long = 250
        private const val START_ANIMATION_DELAY: Long = 250

        /**
         * Observe the given activity's lifecycle.
         *
         * @param activityReference Reference of the colors activity
         * @param view View corresponding to the color information
         */
        fun observe(activityReference: ActivityReference, view: MaterialTextView) {
            val colorInfoView = ColorInfoView(activityReference, view)
            activityReference.get()?.addObserver(colorInfoView)
        }
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
                super.mediumAnimation.run()
            }
        }

    override fun onDestroy() {
        this.stopActivityObservation()
        super.onDestroy()
    }

    override fun onResume() {
        super.onResume()
        this.setText()
        this.setTextColor()
    }

    private fun setText() = super.color?.let { color: Color -> this.view.text = color.name }

    private fun setTextColor() =
        super.color?.let { color: Color -> this.view.setTextColor(color.value) }

    private fun stopActivityObservation() = this.activityReference.get()?.removeObserver(this)
}