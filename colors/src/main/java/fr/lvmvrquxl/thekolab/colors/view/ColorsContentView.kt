package fr.lvmvrquxl.thekolab.colors.view

import androidx.appcompat.app.AppCompatActivity
import fr.lvmvrquxl.thekolab.colors.databinding.ColorsContentBinding
import fr.lvmvrquxl.thekolab.colors.model.Color
import fr.lvmvrquxl.thekolab.colors.utils.Animation
import fr.lvmvrquxl.thekolab.colors.utils.ArgbAnimation
import fr.lvmvrquxl.thekolab.colors.viewmodel.IColorsViewModel
import fr.lvmvrquxl.thekolab.shared.view.LifecycleView

internal class ColorsContentView private constructor(
    private val activity: AppCompatActivity,
    private val content: ColorsContentBinding
) : LifecycleView {
    companion object {
        private const val ONE_SECOND: Long = 1000

        fun create(activity: AppCompatActivity, content: ColorsContentBinding): LifecycleView =
            ColorsContentView(activity, content)
    }

    private val viewModel: IColorsViewModel = IColorsViewModel.instance(this.activity)

    override fun onCreate() = this.observeViewModelColor()

    override fun onStart() = this.setChangeColorsListener()

    private fun observeViewModelColor() =
        this.viewModel.color.observe(this.activity) { color: Color ->
            this.setColorInfo(color)
            val colorValue: Int = color.value
            this.setChangeColorsBackground(colorValue)
        }

    private fun setChangeColorsBackground(color: Int) {
        when (val previousColor: Color? = this.viewModel.previousColor()) {
            null -> this.content.changeColors.apply {
                this.alpha = 0f
                this.isClickable = false
                this.setBackgroundColor(color)
                Animation.create(this@ColorsContentView.activity, this)
                    .medium()
                    .delay(ONE_SECOND)
                    .onEnd { this.isClickable = true }
                    .start()
            }
            else -> ArgbAnimation.show(
                this.content.changeColors,
                ArgbAnimation.Property.BACKGROUND_COLOR,
                previousColor.value,
                color
            )
        }
    }

    private fun setChangeColorsListener() =
        this.content.changeColors.setOnClickListener { this.viewModel.updateColor() }

    private fun setColorInfo(color: Color) {
        this.content.colorInfo.apply {
            Animation.create(this@ColorsContentView.activity, this)
                .emptyAlpha()
                .onEnd {
                    val colorValue: Int = color.value
                    this.text = color.name
                    this.setTextColor(colorValue)
                    Animation.create(this@ColorsContentView.activity, this)
                        .medium()
                        .start()
                }.start()
        }
    }
}