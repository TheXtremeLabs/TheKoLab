package fr.lvmvrquxl.thekolab.colors.view

import androidx.appcompat.app.AppCompatActivity
import fr.lvmvrquxl.thekolab.colors.databinding.ColorsToolbarBinding
import fr.lvmvrquxl.thekolab.colors.model.Color
import fr.lvmvrquxl.thekolab.colors.utils.Animation
import fr.lvmvrquxl.thekolab.colors.utils.ArgbAnimation
import fr.lvmvrquxl.thekolab.colors.viewmodel.IColorsViewModel
import fr.lvmvrquxl.thekolab.shared.view.ActivityView

internal class ColorsToolbarView private constructor(
    private val activity: AppCompatActivity,
    private val toolbar: ColorsToolbarBinding,
    private val viewModel: IColorsViewModel
) : ActivityView<ColorsToolbarBinding>() {
    companion object {
        private const val HALF_SECOND: Long = 500
        private const val ONE_SECOND_AND_A_HALF: Long = 1500

        fun create(
            activity: AppCompatActivity,
            toolbar: ColorsToolbarBinding,
            viewModel: IColorsViewModel
        ): ActivityView<ColorsToolbarBinding> = ColorsToolbarView(activity, toolbar, viewModel)
    }

    override fun onCreate() = this.observeViewModelColor()

    override fun onStart() = this.setBackArrowListener()

    private fun observeViewModelColor() =
        this.viewModel.color.observe(this.activity) { color: Color ->
            val colorValue: Int = color.value
            this.setTitleColor(colorValue)
            this.setBackArrowColor(colorValue)
        }

    private fun setBackArrowColor(color: Int) {
        val previousColor: Color? = this.viewModel.previousColor()
        if (null == previousColor) this.toolbar.backArrow.apply {
            this.alpha = 0f
            this.isClickable = false
            this.setColorFilter(color)
            Animation.create(this@ColorsToolbarView.activity, this)
                .medium()
                .delay(ONE_SECOND_AND_A_HALF)
                .onEnd { this.isClickable = true }
                .start()
        } else ArgbAnimation.show(
            this.toolbar.backArrow,
            ArgbAnimation.Property.COLOR_FILTER,
            previousColor.value,
            color
        )
    }

    private fun setBackArrowListener() =
        this.toolbar.backArrow.setOnClickListener { this.activity.onBackPressed() }

    private fun setTitleColor(color: Int) {
        val previousColor: Color? = this.viewModel.previousColor()
        if (null == previousColor) this.toolbar.title.apply {
            this.alpha = 0f
            this.setTextColor(color)
            Animation.create(this@ColorsToolbarView.activity, this)
                .medium()
                .delay(HALF_SECOND)
                .start()
        } else ArgbAnimation.show(
            this.toolbar.title,
            ArgbAnimation.Property.TEXT_COLOR,
            previousColor.value,
            color
        )
    }
}