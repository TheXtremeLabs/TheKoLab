package fr.lvmvrquxl.thekolab.colors.view

import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textview.MaterialTextView
import fr.lvmvrquxl.thekolab.colors.model.Color
import fr.lvmvrquxl.thekolab.colors.utils.Animation
import fr.lvmvrquxl.thekolab.colors.viewmodel.ColorsActionState
import fr.lvmvrquxl.thekolab.colors.viewmodel.IColorsViewModel
import fr.lvmvrquxl.thekolab.shared.view.AnimatedView
import fr.lvmvrquxl.thekolab.shared.view.LifecycleView

internal class ColorInfoView private constructor(
    private val activity: AppCompatActivity,
    private val view: MaterialTextView
) : AnimatedView {
    companion object {
        private const val EXIT_ANIMATION_DELAY: Long = 250
        private const val START_ANIMATION_DELAY: Long = 250

        fun create(activity: AppCompatActivity, view: MaterialTextView): LifecycleView =
            ColorInfoView(activity, view)
    }

    private val viewModel: IColorsViewModel = IColorsViewModel.instance(this.activity)
    private var color: Color? = null

    override fun onCreate() {
        this.observeColor()
        this.observeActionState()
    }

    override fun onDestroy() {
        this.color = null
    }

    override fun showExitAnimation() = Animation.create(this.activity, this.view)
        .medium()
        .emptyAlpha()
        .delay(EXIT_ANIMATION_DELAY)
        .start()

    override fun showStartAnimation() {
        this.color?.let { color: Color ->
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
            this.color?.let { color: Color ->
                this.view.apply {
                    this.text = color.name
                    this.setTextColor(color.value)
                }
            }
            Animation.create(this.activity, this.view)
                .medium()
                .start()
        }.start()

    private fun observeActionState() =
        this.viewModel.actionState.observe(this.activity) { state: ColorsActionState ->
            when (state) {
                ColorsActionState.START -> this.showStartAnimation()
                ColorsActionState.UPDATE -> this.showUpdateAnimation()
                ColorsActionState.EXIT -> this.showExitAnimation()
                else -> {
                }
            }
        }

    private fun observeColor() = this.viewModel.color.observe(this.activity) { color: Color ->
        this.color = color
    }
}