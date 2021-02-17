package fr.lvmvrquxl.thekolab.colors.view

import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textview.MaterialTextView
import fr.lvmvrquxl.thekolab.colors.model.Color
import fr.lvmvrquxl.thekolab.colors.utils.Animation
import fr.lvmvrquxl.thekolab.colors.utils.ArgbAnimation
import fr.lvmvrquxl.thekolab.colors.viewmodel.IColorsViewModel
import fr.lvmvrquxl.thekolab.shared.view.AnimatedView
import fr.lvmvrquxl.thekolab.shared.view.LifecycleView

internal class TitleView private constructor(
    private val activity: AppCompatActivity,
    private val view: MaterialTextView
) : AnimatedView() {
    companion object {
        private const val HALF_SECOND: Long = 500

        fun create(activity: AppCompatActivity, view: MaterialTextView): LifecycleView =
            TitleView(activity, view)
    }

    private val viewModel: IColorsViewModel = IColorsViewModel.instance(this.activity)
    private var color: Color? = null

    override fun onCreate() {
        this.observeViewModelColor()
    }

    override fun onDestroy() {
        this.color = null
    }

    override fun showEntryAnimation() {
        this.view.apply {
            this.alpha = 0f
            this@TitleView.color?.let { color: Color -> this.setTextColor(color.value) }
        }
        Animation.create(this.activity, this.view)
            .medium()
            .delay(HALF_SECOND)
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

    private fun observeViewModelColor() =
        this.viewModel.color.observe(this.activity) { color: Color ->
            this.color = color
            when (this.viewModel.previousColor()) {
                null -> this.showEntryAnimation()
                else -> this.showUpdateAnimation()
            }
        }
}