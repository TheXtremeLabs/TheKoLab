package fr.lvmvrquxl.thekolab.colors.view

import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import fr.lvmvrquxl.thekolab.colors.model.Color
import fr.lvmvrquxl.thekolab.colors.utils.Animation
import fr.lvmvrquxl.thekolab.colors.utils.ArgbAnimation
import fr.lvmvrquxl.thekolab.colors.viewmodel.ColorsActionState
import fr.lvmvrquxl.thekolab.colors.viewmodel.IColorsViewModel
import fr.lvmvrquxl.thekolab.shared.view.AnimatedView
import fr.lvmvrquxl.thekolab.shared.view.LifecycleView

internal class ChangeColorsView private constructor(
    private val activity: AppCompatActivity,
    private val view: MaterialButton
) : AnimatedView {
    companion object {
        private const val START_ANIMATION_DELAY: Long = 750

        fun create(activity: AppCompatActivity, view: MaterialButton): LifecycleView =
            ChangeColorsView(activity, view)
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
            this@ChangeColorsView.color?.let { color: Color ->
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
        this.viewModel.previousColor()?.let { previousColor: Color ->
            this.color?.let { color: Color ->
                ArgbAnimation.show(
                    this.view,
                    ArgbAnimation.Property.BACKGROUND_COLOR,
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

    private fun setClickListener() = this.view.setOnClickListener { this.viewModel.updateColor() }
}