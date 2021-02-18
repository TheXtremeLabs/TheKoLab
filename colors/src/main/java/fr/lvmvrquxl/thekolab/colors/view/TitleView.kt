package fr.lvmvrquxl.thekolab.colors.view

import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textview.MaterialTextView
import fr.lvmvrquxl.thekolab.colors.model.Color
import fr.lvmvrquxl.thekolab.colors.utils.Animation
import fr.lvmvrquxl.thekolab.colors.utils.ArgbAnimation
import fr.lvmvrquxl.thekolab.colors.viewmodel.ColorsActionState
import fr.lvmvrquxl.thekolab.colors.viewmodel.IColorsViewModel
import fr.lvmvrquxl.thekolab.shared.view.AnimatedView
import fr.lvmvrquxl.thekolab.shared.view.LifecycleView

internal class TitleView private constructor(
    private val activity: AppCompatActivity,
    private val view: MaterialTextView
) : AnimatedView {
    companion object {
        private const val EXIT_ANIMATION_DELAY: Long = 500
        private const val START_ANIMATION_DELAY: Long = 500

        fun create(activity: AppCompatActivity, view: MaterialTextView): LifecycleView =
            TitleView(activity, view)
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
            this@TitleView.color?.let { color: Color -> this.setTextColor(color.value) }
        }
        Animation.create(this.activity, this.view)
            .medium()
            .delay(START_ANIMATION_DELAY)
            .start()
    }

    override fun showUpdateAnimation(): Unit? =
        this.viewModel.previousColor()?.let { previousColor: Color ->
            this.color?.let { color: Color ->
                ArgbAnimation.show(
                    this.view,
                    ArgbAnimation.Property.TEXT_COLOR,
                    previousColor.value,
                    color.value
                )
            }
        }

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