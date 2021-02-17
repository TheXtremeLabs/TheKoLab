package fr.lvmvrquxl.thekolab.colors.view

import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.imageview.ShapeableImageView
import fr.lvmvrquxl.thekolab.colors.model.Color
import fr.lvmvrquxl.thekolab.colors.utils.Animation
import fr.lvmvrquxl.thekolab.colors.utils.ArgbAnimation
import fr.lvmvrquxl.thekolab.colors.viewmodel.IColorsViewModel
import fr.lvmvrquxl.thekolab.shared.view.LifecycleView

internal class ExitView private constructor(
    private val activity: AppCompatActivity,
    private val view: ShapeableImageView
) : LifecycleView {
    companion object {
        private const val ONE_SECOND_AND_A_HALF: Long = 1500

        fun create(activity: AppCompatActivity, view: ShapeableImageView): LifecycleView =
            ExitView(activity, view)
    }

    private val viewModel: IColorsViewModel = IColorsViewModel.instance(this.activity)

    override fun onCreate() = this.observeViewModelColor()

    override fun onStart() = this.setListener()

    private fun observeViewModelColor() =
        this.viewModel.color.observe(this.activity) { color: Color ->
            this.updateColor(color.value)
        }

    private fun setListener() = this.view.setOnClickListener { this.activity.onBackPressed() }

    private fun updateColor(color: Int) {
        val previousColor: Color? = this.viewModel.previousColor()
        if (null == previousColor) this.view.apply {
            this.alpha = 0f
            this.isClickable = false
            this.setColorFilter(color)
            Animation.create(this@ExitView.activity, this)
                .medium()
                .delay(ONE_SECOND_AND_A_HALF)
                .onEnd { this.isClickable = true }
                .start()
        } else ArgbAnimation.show(
            this.view,
            ArgbAnimation.Property.COLOR_FILTER,
            previousColor.value,
            color
        )
    }
}