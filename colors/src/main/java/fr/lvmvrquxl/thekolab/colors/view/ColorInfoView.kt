package fr.lvmvrquxl.thekolab.colors.view

import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textview.MaterialTextView
import fr.lvmvrquxl.thekolab.colors.model.Color
import fr.lvmvrquxl.thekolab.colors.utils.Animation
import fr.lvmvrquxl.thekolab.colors.viewmodel.IColorsViewModel
import fr.lvmvrquxl.thekolab.shared.view.AnimatedView
import fr.lvmvrquxl.thekolab.shared.view.LifecycleView

internal class ColorInfoView private constructor(
    private val activity: AppCompatActivity,
    private val view: MaterialTextView
) : AnimatedView() {
    companion object {
        fun create(activity: AppCompatActivity, view: MaterialTextView): LifecycleView =
            ColorInfoView(activity, view)
    }

    private val viewModel: IColorsViewModel = IColorsViewModel.instance(this.activity)
    private var color: Color? = null

    override fun onCreate() = this.observeViewModelColor()

    override fun onDestroy() {
        this.color = null
    }

    override fun showExitAnimation() = Animation.create(this.activity, this.view)
        .medium()
        .emptyAlpha()
        .start()

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

    private fun observeViewModelColor() =
        this.viewModel.color.observe(this.activity) { color: Color ->
            this.color = color
            this.showUpdateAnimation()
        }
}