package fr.lvmvrquxl.thekolab.colors.view

import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textview.MaterialTextView
import fr.lvmvrquxl.thekolab.colors.model.Color
import fr.lvmvrquxl.thekolab.colors.utils.Animation
import fr.lvmvrquxl.thekolab.colors.utils.ArgbAnimation
import fr.lvmvrquxl.thekolab.colors.viewmodel.IColorsViewModel
import fr.lvmvrquxl.thekolab.shared.view.LifecycleView

internal class TitleView private constructor(
    private val activity: AppCompatActivity,
    private val view: MaterialTextView
) : LifecycleView {
    companion object {
        private const val HALF_SECOND: Long = 500

        fun create(activity: AppCompatActivity, view: MaterialTextView): LifecycleView =
            TitleView(activity, view)
    }

    private val viewModel: IColorsViewModel = IColorsViewModel.instance(this.activity)

    override fun onCreate() {
        this.observeViewModelColor()
    }

    private fun observeViewModelColor() =
        this.viewModel.color.observe(this.activity) { color: Color ->
            this.updateColor(color.value)
        }

    private fun updateColor(color: Int) {
        val previousColor: Color? = this.viewModel.previousColor()
        if (null == previousColor) this.view.apply {
            this.alpha = 0f
            this.setTextColor(color)
            Animation.create(this@TitleView.activity, this)
                .medium()
                .delay(HALF_SECOND)
                .start()
        } else ArgbAnimation.show(
            this.view,
            ArgbAnimation.Property.TEXT_COLOR,
            previousColor.value,
            color
        )
    }
}