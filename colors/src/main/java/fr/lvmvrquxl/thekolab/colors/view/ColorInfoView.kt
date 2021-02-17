package fr.lvmvrquxl.thekolab.colors.view

import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textview.MaterialTextView
import fr.lvmvrquxl.thekolab.colors.model.Color
import fr.lvmvrquxl.thekolab.colors.utils.Animation
import fr.lvmvrquxl.thekolab.colors.viewmodel.IColorsViewModel
import fr.lvmvrquxl.thekolab.shared.view.LifecycleView

internal class ColorInfoView private constructor(
    private val activity: AppCompatActivity,
    private val view: MaterialTextView
) : LifecycleView {
    companion object {
        fun create(activity: AppCompatActivity, view: MaterialTextView): LifecycleView =
            ColorInfoView(activity, view)
    }

    private val viewModel: IColorsViewModel = IColorsViewModel.instance(this.activity)

    override fun onCreate() = this.observeViewModelColor()

    private fun observeViewModelColor() =
        this.viewModel.color.observe(this.activity) { color: Color ->
            this.updateView(color)
        }

    private fun updateView(color: Color) {
        this.view.apply {
            Animation.create(this@ColorInfoView.activity, this)
                .emptyAlpha()
                .onEnd {
                    val colorValue: Int = color.value
                    this.text = color.name
                    this.setTextColor(colorValue)
                    Animation.create(this@ColorInfoView.activity, this)
                        .medium()
                        .start()
                }.start()
        }
    }
}