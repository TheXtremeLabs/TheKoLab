package fr.lvmvrquxl.thekolab.colors.view

import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import fr.lvmvrquxl.thekolab.colors.model.Color
import fr.lvmvrquxl.thekolab.colors.utils.Animation
import fr.lvmvrquxl.thekolab.colors.utils.ArgbAnimation
import fr.lvmvrquxl.thekolab.colors.viewmodel.IColorsViewModel
import fr.lvmvrquxl.thekolab.shared.view.LifecycleView

internal class ChangeColorsView private constructor(
    private val activity: AppCompatActivity,
    private val view: MaterialButton
) : LifecycleView {
    companion object {
        private const val ONE_SECOND: Long = 1000

        fun create(activity: AppCompatActivity, view: MaterialButton): LifecycleView =
            ChangeColorsView(activity, view)
    }

    private val viewModel: IColorsViewModel = IColorsViewModel.instance(this.activity)

    override fun onCreate() = this.observeViewModelColor()

    override fun onStart() = this.setClickListener()

    private fun observeViewModelColor() =
        this.viewModel.color.observe(this.activity) { color: Color ->
            this.updateBackgroundColor(color.value)
        }

    private fun setClickListener() = this.view.setOnClickListener { this.viewModel.updateColor() }

    private fun updateBackgroundColor(color: Int) {
        when (val previousColor: Color? = this.viewModel.previousColor()) {
            null -> this.view.apply {
                this.alpha = 0f
                this.isClickable = false
                this.setBackgroundColor(color)
                Animation.create(this@ChangeColorsView.activity, this)
                    .medium()
                    .delay(ONE_SECOND)
                    .onEnd { this.isClickable = true }
                    .start()
            }
            else -> ArgbAnimation.show(
                this.view,
                ArgbAnimation.Property.BACKGROUND_COLOR,
                previousColor.value,
                color
            )
        }
    }
}